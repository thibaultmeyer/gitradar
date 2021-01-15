CREATE TABLE user
(
    id           UUID              NOT NULL PRIMARY KEY,
    email        VARCHAR(125 CHAR) NOT NULL,
    first_name   VARCHAR(35 CHAR)  NOT NULL,
    last_name    VARCHAR(35 CHAR)  NOT NULL,
    display_name VARCHAR(72 CHAR)  NOT NULL,
    password     VARCHAR(95 CHAR)  NOT NULL,
    created_at   DATETIME          NOT NULL,
    updated_at   DATETIME          NOT NULL,
);


CREATE TABLE git_repo
(
    id         UUID             NOT NULL PRIMARY KEY,
    slug       VARCHAR(50 CHAR) NOT NULL,
    name       VARCHAR(50 CHAR) NOT NULL,
    upstream   TEXT             NOT NULL,
    clone_url  TEXT             NOT NULL,
    created_at DATETIME         NOT NULL,
    updated_at DATETIME         NOT NULL,
);


CREATE TABLE ignored_commit
(
    id          UUID             NOT NULL PRIMARY KEY,
    git_repo_id UUID             NOT NULL REFERENCES git_repo (id),
    commit_sha  VARCHAR(40 CHAR) NOT NULL,
    reason      TEXT,
    created_at  DATETIME         NOT NULL,
    updated_at  DATETIME         NOT NULL,
);


CREATE TABLE scan
(
    id                    UUID      NOT NULL PRIMARY KEY,
    git_repo_id           UUID      NOT NULL REFERENCES git_repo (id),
    status                NUMBER(1) NOT NULL,
    unmerged_commit_çount NUMBER    NOT NULL,
    created_at            DATETIME  NOT NULL,
    updated_at            DATETIME  NOT NULL,
);


CREATE TABLE unmerged_commit
(
    id             UUID             NOT NULL PRIMARY KEY,
    scan_id        UUID             NOT NULL REFERENCES scan (id),
    branch_name    TEXT             NOT NULL,
    commit_sha     VARCHAR(40 CHAR) NOT NULL,
    commit_subject TEXT             NOT NULL,
    created_at     DATETIME         NOT NULL,
    updated_at     DATETIME         NOT NULL,
);
