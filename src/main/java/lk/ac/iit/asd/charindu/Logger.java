package lk.ac.iit.asd.charindu;

import java.text.SimpleDateFormat;
import java.util.Date;

// Singleton Logger
public class Logger {
    private static Logger instance;
    private final SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private LogLevel threshold = LogLevel.INFORM;

    // Private constructor for singleton
    private Logger() {
    }

    // Thread-safe lazy initialization
    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    // Configure log level threshold and enable/disable
    public void configure(LogLevel level) {
        this.threshold = level;
    }

    // Method to check if a certain level is active
    public boolean isLevelEnabled(LogLevel level) {
        return level.getPriority() <= threshold.getPriority();
    }

    // Core logging method
    public void log(LogLevel level, String msg) {
        if (!isLevelEnabled(level)) {
            return;
        }
        String time = fmt.format(new Date());
        String formatted = String.format("[%s] [%s] %s", level, time, msg);
        System.out.println(formatted);
    }

    // Convenience methods
    public void error(String msg) {
        log(LogLevel.ERROR, msg);
    }

    public void debug(String msg) {
        log(LogLevel.DEBUG, msg);
    }

    public void inform(String msg) {
        log(LogLevel.INFORM, msg);
    }
}