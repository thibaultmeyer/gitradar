package com.github.gitradar.database.model;

import io.micronaut.data.annotation.DateCreated;
import io.micronaut.data.annotation.DateUpdated;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * This model represents an ignored commit.
 */
@Entity
@Table(name = "ignored_commit")
public final class IgnoredCommitModel {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "commit_sha")
    private String commitSha;

    @Column(name = "reason")
    private String reason;

    @ManyToOne
    @JoinColumn(name = "git_repo_id", nullable = false)
    private GitRepoModel gitRepo;

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

    public String getCommitSha() {
        return commitSha;
    }

    public void setCommitSha(final String commitSha) {
        this.commitSha = commitSha;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(final String reason) {
        this.reason = reason;
    }

    public GitRepoModel getGitRepo() {
        return gitRepo;
    }

    public void setGitRepo(final GitRepoModel gitRepo) {
        this.gitRepo = gitRepo;
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
