package lk.ac.iit.asd.charindu;

/**
 * LogRepository implementation that writes messages to the console (stdout).
 */
public class ConsoleLogRepository implements LogRepository {
    /**
     * Writes the formatted message to System.out.
     *
     * @param formattedMessage The timestamped and leveled log message.
     */
    public void write(String formattedMessage) {
        System.out.println(formattedMessage);
    }
}
