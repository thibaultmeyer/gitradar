package com.github.gitradar.domain.service;

import com.github.gitradar.domain.entity.GitCherryEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.io.*;
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
     * @param gitDirectory The Git working directory
     * @param upstream     Upstream branch to search for equivalent commits
     * @param head         Working branch
     * @return The command output
     */
    public List<GitCherryEntry> cherry(final String gitDirectory, final String upstream, final String head) {
        final String[] command = {"git", "cherry", "-v", upstream, head};
        final ProcessBuilder builder = new ProcessBuilder(command);
        builder.directory(new File(gitDirectory));

        final List<GitCherryEntry> commitList = new ArrayList<>();
        try {
            final Process process = builder.start();

            try (final InputStream is = process.getInputStream()) {
                final InputStreamReader inputStreamReader = new InputStreamReader(is);
                final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    final String[] line_x = line.split(" ", 3);
                    final GitCherryEntry gitCherryEntry = new GitCherryEntry(line.charAt(0) == '-', line_x[1], line_x[2]);
                    commitList.add(gitCherryEntry);
                }

                inputStreamReader.close();
                bufferedReader.close();
            }
        } catch (final IOException ex) {
            LOGGER.error("Can't cherry Git repository", ex);
        }

        return commitList;
    }

    /**
     * Clone a repository into the given repository.
     *
     * @param cloneUrl               The URL of the repository to clone from
     * @param gitRootDirectory       The root Git directory (Where all repositories are cloned)
     * @param gitTargetDirectoryName The name of a new directory to clone into
     */
    public void clone(final String cloneUrl, final String gitRootDirectory, final String gitTargetDirectoryName) {
        final String[] command = {"git", "clone", "--quiet", cloneUrl, gitTargetDirectoryName};
        final ProcessBuilder builder = new ProcessBuilder(command);
        builder.directory(new File(gitRootDirectory));

        try {
            final Process process = builder.start();
            process.waitFor();
        } catch (final IOException | InterruptedException ex) {
            LOGGER.error("Can't clone Git repository from {}", cloneUrl, ex);
        }
    }

    /**
     * Fetch branches and/or tags (collectively, "refs") from one or more other repositories, along with
     * the objects necessary to complete their histories. Remote-tracking branches are updated.
     *
     * @param gitDirectory The Git working directory
     */
    public void fetch(final String gitDirectory) {
        final String[] command = {"git", "fetch"};
        final ProcessBuilder builder = new ProcessBuilder(command);
        builder.directory(new File(gitDirectory));

        try {
            final Process process = builder.start();
            process.waitFor();
        } catch (final IOException | InterruptedException ex) {
            LOGGER.error("Can't fetch Git repository", ex);
        }
    }

    /**
     * List available branches.
     *
     * @param gitDirectory The Git working directory
     * @return All available branches
     */
    public List<String> branch(final String gitDirectory) {
        final String[] command = {"git", "branch", "--list", "--remote"};
        final ProcessBuilder builder = new ProcessBuilder(command);
        builder.directory(new File(gitDirectory));

        final List<String> branchList = new ArrayList<>();
        try {
            final Process process = builder.start();

            try (final InputStream is = process.getInputStream()) {
                final InputStreamReader inputStreamReader = new InputStreamReader(is);
                final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    branchList.add(line.replaceAll("^\\s+", ""));
                }

                inputStreamReader.close();
                bufferedReader.close();
            }
        } catch (final IOException ex) {
            LOGGER.error("Can't cherry Git repository", ex);
        }

        return branchList;
    }
}
