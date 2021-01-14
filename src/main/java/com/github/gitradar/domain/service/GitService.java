package com.github.gitradar.domain.service;

import com.github.gitradar.domain.entity.GitCherryEntry;
import com.github.gitradar.job.HelloWorldJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Service: Git.
 */
@Singleton
public final class GitService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GitService.class);

    /**
     * Build a new instance.
     */
    public GitService() {
    }

    /**
     * Find commits yet to be applied to upstream.
     *
     * @param workingDirectory The Git working directory
     * @param upstream         Upstream branch to search for equivalent commits
     * @param head             Working branch
     * @return The command output
     */
    public List<GitCherryEntry> cherry(final String workingDirectory, final String upstream, final String head) {
        final String[] command = {"git", "cherry", "-v", upstream, head};
        final ProcessBuilder builder = new ProcessBuilder(command);
        builder.directory(new File(workingDirectory));

        final List<GitCherryEntry> commitList = new ArrayList<>();
        try {
            final BufferedReader is = new BufferedReader(new InputStreamReader(builder.start().getInputStream()));
            String line;
            while ((line = is.readLine()) != null) {
                final String[] line_x = line.split(" ", 3);
                final GitCherryEntry gitCherryEntry = new GitCherryEntry(line.charAt(0) == '-', line_x[1], line_x[2]);
                commitList.add(gitCherryEntry);
            }
        } catch (final IOException ex) {
            LOGGER.error("Can't cherry Git repository", ex);
        }

        return commitList;
    }

    /**
     * Fetch branches and/or tags (collectively, "refs") from one or more other repositories, along with
     * the objects necessary to complete their histories. Remote-tracking branches are updated.
     *
     * @param workingDirectory The Git working directory
     */
    public void fetch(final String workingDirectory) {
        final String[] command = {"git", "fetch"};
        final ProcessBuilder builder = new ProcessBuilder(command);
        builder.directory(new File(workingDirectory));

        try {
            builder.start();
        } catch (final IOException ex) {
            LOGGER.error("Can't fetch Git repository", ex);
        }
    }
}
