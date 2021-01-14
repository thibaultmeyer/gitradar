package com.github.gitradar.database.model;

import io.micronaut.data.annotation.DateCreated;
import io.micronaut.data.annotation.DateUpdated;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
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

    @OneToMany(targetEntity = IgnoredCommitModel.class, mappedBy = "gitRepoModel")
    private List<IgnoredCommitModel> ignoredCommitModelList;

    @OneToMany(targetEntity = ScanModel.class, mappedBy = "gitRepoModel")
    private List<ScanModel> scanModelList;

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

    public List<IgnoredCommitModel> getIgnoredCommitModelList() {
        return ignoredCommitModelList;
    }

    public void setIgnoredCommitModelList(final List<IgnoredCommitModel> ignoredCommitModelList) {
        this.ignoredCommitModelList = ignoredCommitModelList;
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
