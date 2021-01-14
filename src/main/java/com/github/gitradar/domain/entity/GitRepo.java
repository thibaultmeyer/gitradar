package com.github.gitradar.domain.entity;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Domain entity: Git repository.
 */
public final class GitRepo {

    private UUID id;

    private String slug;

    private String name;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    /**
     * Build a new instance.
     *
     * @param slug The URL slug
     * @param name The name of the repository
     */
    public GitRepo(final String slug,
                   final String name) {
        this.slug = slug;
        this.name = name;
    }

    /**
     * Build a new instance.
     *
     * @param id        The unique ID
     * @param slug      The URL slug
     * @param name      The name of the repository
     * @param createdAt When the user has been created
     * @param updatedAt When the user has been updated for the last time
     */
    public GitRepo(final UUID id,
                   final String slug,
                   final String name,
                   final LocalDateTime createdAt,
                   final LocalDateTime updatedAt) {
        this.id = id;
        this.slug = slug;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
