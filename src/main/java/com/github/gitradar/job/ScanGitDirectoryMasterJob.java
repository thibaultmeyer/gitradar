package com.github.gitradar.job;

import com.github.gitradar.domain.entity.GitRepo;
import com.github.gitradar.domain.entity.Scan;
import com.github.gitradar.domain.entity.UnmergedCommit;
import com.github.gitradar.domain.enumeration.ScanStatus;
import com.github.gitradar.domain.service.GitRepoService;
import com.github.gitradar.domain.service.GitService;
import com.github.gitradar.domain.service.ScanService;
import com.github.gitradar.domain.service.UnmergedCommitService;
import io.micronaut.context.annotation.Property;
import io.micronaut.core.util.StringUtils;
import io.micronaut.scheduling.annotation.Scheduled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Hello World Job.
 */
@Singleton
public class ScanGitDirectoryMasterJob {

    private static final Logger LOG = LoggerFactory.getLogger(ScanGitDirectoryMasterJob.class);

    private final String gitDataDirectory;

    private final GitRepoService gitRepoService;
    private final GitService gitService;
    private final ScanService scanService;
    private final UnmergedCommitService unmergedCommitService;

    /**
     * Build a new instance.
     *
     * @param gitDataDirectory      The directory containing all Git repositories
     * @param gitService            Handle to the Git service
     * @param gitRepoService        Handle to the Git repository service
     * @param scanService           Handle to the Git repository scan service
     * @param unmergedCommitService Handle to the unmerged commit service
     */
    @Inject
    public ScanGitDirectoryMasterJob(@Property(name = "gitradar.git_data_directory") final String gitDataDirectory,
                                     final GitRepoService gitRepoService,
                                     final GitService gitService,
                                     final ScanService scanService,
                                     final UnmergedCommitService unmergedCommitService) {
        if (StringUtils.isEmpty(gitDataDirectory)) {
            this.gitDataDirectory = System.getProperty("java.io.tmpdir");
        } else {
            this.gitDataDirectory = gitDataDirectory;
        }

        this.gitRepoService = gitRepoService;
        this.gitService = gitService;
        this.scanService = scanService;
        this.unmergedCommitService = unmergedCommitService;
    }

    @Scheduled(fixedDelay = "1h", initialDelay = "5s")
    public void process() {

        for (final GitRepo gitRepo : gitRepoService.findAll()) {
            // Create a new Scan entry
            final Scan scan = scanService.persist(new Scan(gitRepo.getId(), ScanStatus.IN_PROGRESS));

            // Build Git repository path
            final Path repoDirectoryPath = Paths.get(gitDataDirectory, gitRepo.getId().toString()).toAbsolutePath();

            // Clone or fetch
            if (!Files.isDirectory(repoDirectoryPath)) {
                LOG.info("Clone the Git repository '{}'", gitRepo.getName());
                gitService.clone(gitRepo.getCloneUrl(), gitDataDirectory, gitRepo.getId().toString());
            } else {
                LOG.info("Fetch the Git repository '{}'", gitRepo.getName());
                gitService.fetch(repoDirectoryPath.toString());
            }

            // Retrieves branches
            final List<String> branchList = gitService.branch(repoDirectoryPath.toString()).stream()
                .filter(branchName -> !branchName.contains("HEAD"))
                .filter(branchName -> !branchName.endsWith(gitRepo.getUpstream()))
                .collect(Collectors.toList());

            // Retrieve unmerged commit for all needed branches
            int unmergedCommitCount = 0;
            for (final String branch : branchList) {
                LOG.info("Cherry the Git repository '{}' ({} / {})", gitRepo.getName(), gitRepo.getUpstream(), branch);

                final List<UnmergedCommit> unmergedCommitList = new ArrayList<>();
                gitService.cherry(repoDirectoryPath.toString(), "master", branch).stream()
                    .filter(gitCherryEntry -> !gitCherryEntry.haveEquivalentInUpstream)
                    .forEach(gitCherryEntry -> {
                        final UnmergedCommit unmergedCommit = new UnmergedCommit(scan.getId(),
                            branch,
                            gitCherryEntry.commitSha,
                            gitCherryEntry.commitSubject);

                        unmergedCommitList.add(unmergedCommit);
                    });

                if (!unmergedCommitList.isEmpty()) {
                    unmergedCommitService.persistAll(unmergedCommitList);
                    unmergedCommitCount += unmergedCommitList.size();
                }
            }

            scan.setUnmergedCommitCount(unmergedCommitCount);
            scan.setStatus(ScanStatus.COMPLETE);
            scanService.persist(scan);
        }
    }
}
