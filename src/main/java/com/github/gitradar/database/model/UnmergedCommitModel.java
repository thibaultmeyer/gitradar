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
    private ScanModel scanModel;

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
}
