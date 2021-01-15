package com.github.gitradar.rest.dto;

import com.github.gitradar.domain.enumeration.ScanStatus;
import io.micronaut.core.annotation.Introspected;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO : Home (Response).
 */
@Introspected
public final class HomeResponse {

    private List<Repository> repositoryList;


    public List<Repository> getRepositoryList() {
        return repositoryList;
    }

    public void setRepositoryList(final List<Repository> repositoryList) {
        this.repositoryList = repositoryList;
    }

    /**
     * Nested class: Repository.
     */
    @Introspected
    public static final class Repository {

        private String slug;

        private String name;

        private String lastScanStatus;

        private int lastScanUnmergedCommit;

        private LocalDateTime lastScan;

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

        public String getLastScanStatus() {
            return lastScanStatus;
        }

        public void setLastScanStatus(final String lastScanStatus) {
            this.lastScanStatus = lastScanStatus;
        }

        public int getLastScanUnmergedCommit() {
            return lastScanUnmergedCommit;
        }

        public void setLastScanUnmergedCommit(final int lastScanUnmergedCommit) {
            this.lastScanUnmergedCommit = lastScanUnmergedCommit;
        }

        public LocalDateTime getLastScan() {
            return lastScan;
        }

        public void setLastScan(final LocalDateTime lastScan) {
            this.lastScan = lastScan;
        }
    }
}
