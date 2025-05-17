package lk.ac.iit.asd.charindu;

/**
 * Defines log levels with associated priority for filtering.
 * Lower priority values indicate higher severity.
 */
public enum LogLevel {
    /**
     * Critical errors requiring immediate attention.
     */
    CRITICAL(5),
    /**
     * Error level, high severity.
     */
    ERROR(10),
    /**
     * Alarm level for potential issues.
     */
    ALARM(15),
    /**
     * Debug level for detailed diagnostic messages.
     */
    DEBUG(20),
    /**
     * Informational messages about normal operations.
     */
    INFORM(30);


    private final int priority;

    /**
     * Constructs a LogLevel with the given numeric priority.
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