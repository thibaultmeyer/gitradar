package com.github.gitradar.rest.controller;

import com.github.gitradar.domain.entity.User;
import com.github.gitradar.domain.service.GitService;
import com.github.gitradar.domain.service.UserService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import javax.inject.Inject;
import java.util.Optional;
import java.util.UUID;

/**
 * Controller: User.
 */
@Controller("/")
public final class UserController {

    private final UserService userService;
    private final GitService gitService;

    /**
     * Build a new instance.
     *
     * @param userService handle to the user service
     */
    @Inject
    public UserController(final UserService userService, final GitService gitService) {
        this.userService = userService;
        this.gitService = gitService;
    }

    /**
     * Find a user from their unique ID.
     *
     * @param userId The user's unique ID
     * @return The user
     */
    @Get("/{userId}")
    public Optional<User> findById(final UUID userId) {
        return userService.findById(userId);
    }
}
