package com.github.gitradar.domain.entity;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Domain entity: Unmerged commit.
 */
public final class UnmergedCommit {

    private UUID id;

    private UUID scanId;

    private String branch;

    private String commitSha;

    private String commitSubject;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    /**
     * Build a new instance.
     *
     * @param scanId        The scan unique ID
     * @param branch        The branch name where is located the unmerged commit
     * @param commitSha     The unmerged commit SHA
     * @param commitSubject The unmerged commit subject
     */
    public UnmergedCommit(final UUID scanId,
                          final String branch,
                          final String commitSha,
                          final String commitSubject) {
        this.scanId = scanId;
        this.branch = branch;
        this.commitSha = commitSha;
        this.commitSubject = commitSubject;
    }

    /**
     * Build a new instance.
     *
     * @param id            The unique ID
     * @param scanId        The scan unique ID
     * @param branch        The branch name where is located the unmerged commit
     * @param commitSha     The unmerged commit SHA
     * @param commitSubject The unmerged commit subject
     * @param createdAt     When the user has been created
     * @param updatedAt     When the user has been updated for the last time
     */
    public UnmergedCommit(final UUID id,
                          final UUID scanId,
                          final String branch,
                          final String commitSha,
                          final String commitSubject,
                          final LocalDateTime createdAt,
                          final LocalDateTime updatedAt) {
        this.id = id;
        this.scanId = scanId;
        this.branch = branch;
        this.commitSha = commitSha;
        this.commitSubject = commitSubject;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public UUID getScanId() {
        return scanId;
    }

    public void setScanId(final UUID scanId) {
        this.scanId = scanId;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(final String branch) {
        this.branch = branch;
    }

    public String getCommitSha() {
        return commitSha;
    }

    public void setCommitSha(final String commitSha) {
        this.commitSha = commitSha;
    }

    public String getCommitSubject() {
        return commitSubject;
    }

    public void setCommitSubject(final String commitSubject) {
        this.commitSubject = commitSubject;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(final LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
