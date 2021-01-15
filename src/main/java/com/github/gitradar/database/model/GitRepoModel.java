package com.github.gitradar.database.model;

import io.micronaut.data.annotation.DateCreated;
import io.micronaut.data.annotation.DateUpdated;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * This model represents a Git repository.
 */
@Entity
@Table(name = "git_repo")
public class GitRepoModel {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "slug")
    private String slug;

    @Column(name = "name")
    private String name;

    @Column(name = "upstream")
    private String upstream;

    @Column(name = "clone_url")
    private String cloneUrl;

    @OneToMany(targetEntity = IgnoredCommitModel.class, mappedBy = "gitRepo", orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<IgnoredCommitModel> ignoredCommitList;

    @OneToMany(targetEntity = ScanModel.class, mappedBy = "gitRepo", orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<ScanModel> scanList;

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

    public String getSlug() {
        return slug;
    }

    public void setSlug(final String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getUpstream() {
        return upstream;
    }

    public void setUpstream(final String upstream) {
        this.upstream = upstream;
    }

    public String getCloneUrl() {
        return cloneUrl;
    }

    public void setCloneUrl(final String cloneUrl) {
        this.cloneUrl = cloneUrl;
    }

    public Set<IgnoredCommitModel> getIgnoredCommitList() {
        return ignoredCommitList;
    }

    public void setIgnoredCommitList(final Set<IgnoredCommitModel> ignoredCommitList) {
        this.ignoredCommitList = ignoredCommitList;
    }

    public Set<ScanModel> getScanList() {
        return scanList;
    }

    public void setScanList(final Set<ScanModel> scanList) {
        this.scanList = scanList;
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
