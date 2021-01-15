package com.github.gitradar.domain.enumeration;

/**
 * All available scan status.
 */
public enum ScanStatus {

    /**
     * Scan is in progress.
     */
    IN_PROGRESS(1),

    /**
     * Scan is now completed (Success).
     */
    COMPLETE(2),

    /**
     * The scan was aborted due to an error.
     */
    ABORTED(3);

    private final int databaseValue;

    /**
     * Build a new instance.
     *
     * @param databaseValue Value used when the enumeration is stored in database
     */
    ScanStatus(final int databaseValue) {
        this.databaseValue = databaseValue;
    }

    /**
     * Retrieve enumeration from the database value.
     *
     * @param databaseValue The database value to use
     * @return The enumeration in case of success, otherwise, {@code null}
     */
    public static ScanStatus fromDatabaseValue(final Integer databaseValue) {
        for (final ScanStatus status : ScanStatus.values()) {
            if (status.databaseValue == databaseValue) {
                return status;
            }
        }

        throw new IllegalArgumentException("Can't retrieve ScanStatus enum value from databaseValue=" + databaseValue);
    }

    /**
     * Get the value used when the enumeration is stored in database.
     *
     * @return The value as integer
     */
    public int getDatabaseValue() {
        return databaseValue;
    }
}
