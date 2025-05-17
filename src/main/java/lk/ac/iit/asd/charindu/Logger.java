package lk.ac.iit.asd.charindu;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Singleton Logger for printing timestamped messages to the console.
 * Supports configurable log levels and convenience methods.
 */
public class Logger {
    /**
     * Singleton instance.
     */
    private static Logger instance;
    /**
     * Date formatter for log entry timestamps.
     */
    private final SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    /**
     * Current threshold for which log levels to display.
     */
    private LogLevel threshold = LogLevel.INFORM;
    private boolean enabled = true;
    private LogRepository repo;

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
     * Configures the minimum log level threshold.
     * Messages with a priority higher than this will be ignored.
     *
     * @param level LogLevel threshold to set.
     */
    public void configure(LogLevel level, boolean enabled, LogRepository repo) {
        this.threshold = level;
        this.enabled = enabled;
        this.repo = repo;
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
     * Formats the message with timestamp and prints to console.
     *
     * @param level LogLevel for the message.
     * @param msg   Message content.
     */
    public void log(LogLevel level, String msg) {
        if (!isLevelEnabled(level) || repo == null) {
            return;
        }
        String time = fmt.format(new Date());
        String formatted = String.format("[%s] [%s] %s", level, time, msg);
        repo.write(formatted);
    }

    /**
     * Logs an ERROR level message.
     */
    public void error(String msg) {
        log(LogLevel.ERROR, msg);
    }

    /**
     * Logs a DEBUG level message.
     */
    public void debug(String msg) {
        log(LogLevel.DEBUG, msg);
    }

    /**
     * Logs an INFORM level message.
     */
    public void inform(String msg) {
        log(LogLevel.INFORM, msg);
    }

    public void alarm(String msg) {
        log(LogLevel.ALARM, msg);
    }

    public void critical(String msg) {
        log(LogLevel.CRITICAL, msg);
    }
}