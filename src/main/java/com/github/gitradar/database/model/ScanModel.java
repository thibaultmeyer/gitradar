package com.github.gitradar.database.model;

import com.github.gitradar.domain.enumeration.ScanStatus;
import io.micronaut.data.annotation.DateCreated;
import io.micronaut.data.annotation.DateUpdated;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * This model represents a scan.
 */
@Entity
@Table(name = "scan")
public final class ScanModel {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "git_repo_id", nullable = false)
    private GitRepoModel gitRepo;

    @Column(name = "status", nullable = false)
    private ScanStatus status;

    @Column(name = "unmerged_commit_çount", nullable = false)
    private int unmergedCommitCount;

    @OneToMany(targetEntity = UnmergedCommitModel.class, mappedBy = "scan", fetch = FetchType.LAZY)
    private List<UnmergedCommitModel> unmergedCommitList;

    @DateCreated
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @DateCreated
    @DateUpdated
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public GitRepoModel getGitRepo() {
        return gitRepo;
    }

    public void setGitRepo(final GitRepoModel gitRepo) {
        this.gitRepo = gitRepo;
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
