package lk.ac.iit.asd.charindu;

/**
 * Defines log levels with associated priority for filtering.
 * Higher priority values indicate lower severity.
 */
public enum LogLevel {
    /**
     * Error level, the highest severity.
     */
    ERROR(10),
    /**
     * Debug level for detailed messages.
     */
    DEBUG(20),
    /**
     * Informational level for general messages.
     */
    INFORM(30);

    /**
     * Numeric priority for comparison.
     */
    private final int priority;

    /**
     * Constructor to assign priority.
     *
     * @param p Numeric priority.
     */
    LogLevel(int p) {
        this.priority = p;
    }

    /**
     * Retrieves the numeric priority of this log level.
     *
     * @return Priority as int.
     */
    public int getPriority() {
        return priority;
    }
}