package com.github.gitradar.database.model;

import io.micronaut.data.annotation.DateCreated;
import io.micronaut.data.annotation.DateUpdated;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * This model represents an unmerged commit.
 */
@Entity
@Table(name = "unmerged_commit")
public class UnmergedCommitModel {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "scan_id", nullable = false)
    private ScanModel scan;

    @Column(name = "branch_name")
    private String branch;

    @Column(name = "commit_sha")
    private String commitSha;

    @Column(name = "commit_subject")
    private String commitSubject;

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

    public ScanModel getScan() {
        return scan;
    }

    public void setScan(final ScanModel scan) {
        this.scan = scan;
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
