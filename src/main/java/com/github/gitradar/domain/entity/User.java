package com.github.gitradar.domain.entity;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Domain entity: User.
 */
public final class User {

    private UUID id;

    private String email;

    private String firstName;

    private String lastName;

    private String displayName;

    private String password;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    /**
     * Build a new instance.
     *
     * @param email       The email address
     * @param firstName   The first name
     * @param lastName    The last name
     * @param displayName The display name
     */
    public User(final String email,
                final String firstName,
                final String lastName,
                final String displayName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.displayName = displayName;
    }

    /**
     * Build a new instance.
     *
     * @param id          The unique ID
     * @param email       The email address
     * @param firstName   The first name
     * @param lastName    The last name
     * @param displayName The display name
     * @param password    The hashed password
     * @param createdAt   When the user has been created
     * @param updatedAt   When the user has been updated for the last time
     */
    public User(final UUID id,
                final String email,
                final String firstName,
                final String lastName,
                final String displayName,
                final String password,
                final LocalDateTime createdAt,
                final LocalDateTime updatedAt) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.displayName = displayName;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Retrieves the user's unique ID.
     *
     * @return The user's unique ID
     */
    public UUID getId() {
        return id;
    }

    /**
     * Sets the user's unique ID.
     *
     * @param id The new user's unique ID
     */
    public void setId(final UUID id) {
        this.id = id;
    }

    /**
     * Retrieves the email address.
     *
     * @return The email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address.
     *
     * @param email The new email address
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * Retrieves the first name.
     *
     * @return The first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name.
     *
     * @param firstName The new first name
     */
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    /**
     * Retrieves the last name.
     *
     * @return The last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name.
     *
     * @param lastName The new last name
     */
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    /**
     * Retrieves the display name.
     *
     * @return The display name
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Sets the display name.
     *
     * @param displayName The new display last name
     */
    public void setDisplayName(final String displayName) {
        this.displayName = displayName;
    }

    /**
     * Checks if the given password is correct.
     *
     * @return The hashed password
     */
    public boolean verifyPassword(final String password) {
        return false;
    }

    /**
     * Retrieves the hashed password.
     *
     * @return The new hashed password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the hashed password.
     *
     * @param password The new hashed password
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     * Retrieves the creation date.
     *
     * @return The creation date
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Retrieves the last update date.
     *
     * @return The last update date
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
