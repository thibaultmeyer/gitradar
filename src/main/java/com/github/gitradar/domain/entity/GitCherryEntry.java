package com.github.gitradar.domain.entity;

/**
 * Domain entity: Git cherry command response entry.
 */
public final class GitCherryEntry {

    public final boolean haveEquivalentInUpstream;

    public final String commitSha;

    public final String commitSubject;

    /**
     * Build a new instance.
     *
     * @param haveEquivalentInUpstream indicate if the commit have an equivalent in upstream
     * @param commitSha                The commit SHA
     * @param commitSubject            The commit subject
     */
    public GitCherryEntry(final boolean haveEquivalentInUpstream,
                          final String commitSha,
                          final String commitSubject) {
        this.haveEquivalentInUpstream = haveEquivalentInUpstream;
        this.commitSha = commitSha;
        this.commitSubject = commitSubject;
    }
}
