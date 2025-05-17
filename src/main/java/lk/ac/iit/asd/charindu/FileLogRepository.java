package lk.ac.iit.asd.charindu;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Logs messages by appending them to a text file.
 */
public class FileLogRepository implements LogRepository {
    private final String path;

    /**
     * Constructs a FileLogRepository for the given file path.
     *
     * @param path Path to the log file.
     */
    public FileLogRepository(String path) {
        this.path = path;
    }

    /**
     * Appends the formatted message to the file, creating or opening it as needed.
     *
     * @param formattedMessage The timestamped and leveled log message.
     */
    public void write(String formattedMessage) {
        try (FileWriter fw = new FileWriter(path, true)) {
            fw.write(formattedMessage + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
