package com.github.gitradar.domain.entity;

import com.github.gitradar.domain.enumeration.ScanStatus;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Domain entity: Git repository scan.
 */
public final class Scan {

    private UUID id;

    private UUID gitRepoId;

    private ScanStatus status;

    private int unmergedCommitCount;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    /**
     * Build a new instance.
     *
     * @param gitRepoId The Git repository unique ID
     * @param status    The scan status
     */
    public Scan(final UUID gitRepoId,
                final ScanStatus status) {
        this.gitRepoId = gitRepoId;
        this.status = status;
        this.unmergedCommitCount = -1;
    }

    /**
     * Build a new instance.
     *
     * @param id                  The unique ID
     * @param gitRepoId           The Git repository unique ID
     * @param status              The scan status
     * @param unmergedCommitCount The number of unmerged commit detected
     * @param createdAt           When the user has been created
     * @param updatedAt           When the user has been updated for the last time
     */
    public Scan(final UUID id,
                final UUID gitRepoId,
                final ScanStatus status,
                final int unmergedCommitCount,
                final LocalDateTime createdAt,
                final LocalDateTime updatedAt) {
        this.id = id;
        this.gitRepoId = gitRepoId;
        this.status = status;
        this.unmergedCommitCount = unmergedCommitCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public UUID getGitRepoId() {
        return gitRepoId;
    }

    public void setGitRepoId(final UUID gitRepoId) {
        this.gitRepoId = gitRepoId;
    }

    public ScanStatus getStatus() {
        return status;
    }

    public void setStatus(final ScanStatus status) {
        this.status = status;
    }

    public int getUnmergedCommitCount() {
        return unmergedCommitCount;
    }

    public void setUnmergedCommitCount(final int unmergedCommitCount) {
        this.unmergedCommitCount = unmergedCommitCount;
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
