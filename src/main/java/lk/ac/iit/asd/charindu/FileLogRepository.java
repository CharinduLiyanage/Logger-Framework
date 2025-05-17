package lk.ac.iit.asd.charindu;

import java.io.FileWriter;
import java.io.IOException;

public class FileLogRepository implements LogRepository {
    private final String path;

    public FileLogRepository(String path) {
        this.path = path;
    }

    public void write(String formattedMessage) {
        try (FileWriter fw = new FileWriter(path, true)) {
            fw.write(formattedMessage + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
