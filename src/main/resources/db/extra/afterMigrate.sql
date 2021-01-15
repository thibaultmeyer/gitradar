INSERT INTO user (id, email, first_name, last_name, display_name, password, created_at, updated_at)
VALUES ('36b6a8c2-1efa-49cc-9d7e-8489a6a5e200', 'charlie.root@domain.local', 'Charlie', 'ROOT', 'root',
        'pbkdf2_hmacsha256$10000$5fa8a951059416ff$68c6863134c09d85b5edc1777d68571ee695ef24',
        '2020-09-20 12:00:00.000', '2020-09-20 12:00:00.000');

INSERT INTO git_repo(`id`, `slug`, `name`, `upstream`, `clone_url`, `created_at`, `updated_at`)
VALUES ('3df3fd72-acf1-423c-b401-f0ebed43e10d', 'go-gitea', 'Gitea', 'master', 'https://github.com/go-gitea/gitea.git',
        '2020-09-20 12:00:00.000', '2020-09-20 12:00:00.000');

INSERT INTO git_repo(`id`, `slug`, `name`, `upstream`, `clone_url`, `created_at`, `updated_at`)
VALUES ('a44ff6a5-e9ed-4d97-8a4e-db63d42ec05f', 'chip8-emulator', 'CHIP-8 Emulator', 'master',
        'https://github.com/thibaultmeyer/chip8-emulator.git', '2020-09-20 12:00:00.000', '2020-09-20 12:00:00.000');

INSERT INTO git_repo(`id`, `slug`, `name`, `upstream`, `clone_url`, `created_at`, `updated_at`)
VALUES ('9e923b7d-ff7c-4d28-9a30-82963c56ebbe', 'linked-list-c', 'Linked list (C)', 'master',
        'https://github.com/thibaultmeyer/linked-list-c.git', '2020-09-20 12:00:00.000', '2020-09-20 12:00:00.000');
