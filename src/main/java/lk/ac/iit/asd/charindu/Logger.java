package lk.ac.iit.asd.charindu;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Singleton Logger for formatting and routing log messages.
 * Supports level filtering, timestamping, and multiple repository backends.
 */
public class Logger {
    /**
     * Date formatter for log entry timestamps.
     */
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    /**
     * Singleton instance.
     */
    private static Logger instance;
    /**
     * Current threshold for which log levels to display.
     */
    private LogLevel threshold;
    /**
     * Whether logging is enabled.
     */
    private boolean enabled;
    /**
     * The repository to which formatted messages are written.
     */
    private LogRepository repository;

    /**
     * Private constructor to prevent external instantiation.
     */
    private Logger() {
    }

    /**
     * Returns the singleton Logger instance, creating it if necessary (thread-safe).
     *
     * @return Logger instance.
     */
    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    /**
     * Configures the logger with level, enabled a flag, and repository.
     *
     * @param level      Minimum level to log.
     * @param enabled    Whether logging is active.
     * @param repository Backend repository for formatted messages.
     */
    public synchronized void configure(LogLevel level, boolean enabled, LogRepository repository) {
        this.threshold = level;
        this.enabled = enabled;
        this.repository = repository;
    }

    /**
     * Checks if the given log level is enabled according to the threshold.
     *
     * @param level LogLevel to check.
     * @return True if level priority <= threshold; false otherwise.
     */
    public boolean isLevelEnabled(LogLevel level) {
        return enabled && level.getPriority() <= threshold.getPriority();
    }

    /**
     * Logs a message at the specified log level if enabled.
     * Formats the message with timestamp and write to the repository.
     *
     * @param level LogLevel for the message.
     * @param msg   Message content.
     */
    public void log(LogLevel level, String msg) {
        if (!isLevelEnabled(level) || repository == null) {
            return;
        }
        String timestamp = DATE_FORMAT.format(new Date());
        String formatted = String.format("[%s] [%s] %s", timestamp, level, msg);
        repository.write(formatted);
    }

    /**
     * Logs an error-level message.
     *
     * @param msg Debug message text.
     */
    public void error(String msg) {
        log(LogLevel.ERROR, msg);
    }

    /**
     * Logs a debug-level message.
     *
     * @param msg Debug message text.
     */
    public void debug(String msg) {
        log(LogLevel.DEBUG, msg);
    }

    /**
     * Logs an informational message.
     *
     * @param msg Informational message text.
     */
    public void inform(String msg) {
        log(LogLevel.INFORM, msg);
    }

    /**
     * Logs an alarm-level message.
     *
     * @param msg Alarm message text.
     */
    public void alarm(String msg) {
        log(LogLevel.ALARM, msg);
    }

    /**
     * Logs a critical-level message.
     *
     * @param msg Critical message text.
     */
    public void critical(String msg) {
        log(LogLevel.CRITICAL, msg);
    }
}