package lk.ac.iit.asd.charindu;

/**
 * Defines the contract for log repositories.
 * Implementations handle the persistence of formatted log messages.
 */
public interface LogRepository {
    /**
     * Writes a formatted log message to the underlying store.
     *
     * @param formattedMessage The message, including timestamp and level.
     */
    void write(String formattedMessage);
}
