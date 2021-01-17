package com.github.gitradar.rest.dto;

import com.github.gitradar.domain.enumeration.ScanStatus;
import io.micronaut.core.annotation.Introspected;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * DTO : Repository Information (Response).
 */
@Introspected
public final class RepositoryInformationResponse {

    private String repositoryName;

    private Scan lastScan;

    public String getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(final String repositoryName) {
        this.repositoryName = repositoryName;
    }

    public Scan getLastScan() {
        return lastScan;
    }

    public void setLastScan(final Scan lastScan) {
        this.lastScan = lastScan;
    }

    @Introspected
    public static final class Scan {

        private UUID id;

        private ScanStatus status;

        private int unmergedCommitCount;

        private List<UnmergedCommit> unmergedCommitList;

        private LocalDateTime createdAt;

        public UUID getId() {
            return id;
        }

        public void setId(final UUID id) {
            this.id = id;
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

        public List<UnmergedCommit> getUnmergedCommitList() {
            return unmergedCommitList;
        }

        public void setUnmergedCommitList(final List<UnmergedCommit> unmergedCommitList) {
            this.unmergedCommitList = unmergedCommitList;
        }

        public LocalDateTime getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(final LocalDateTime createdAt) {
            this.createdAt = createdAt;
        }
    }

    @Introspected
    public static final class UnmergedCommit {

        private String branchName;

        private String commitSha;

        private String commitSubject;

        public String getBranchName() {
            return branchName;
        }

        public void setBranchName(final String branchName) {
            this.branchName = branchName;
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
    }
}
