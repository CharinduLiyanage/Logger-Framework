package lk.ac.iit.asd.charindu;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

/**
 * Logs messages to an XML file, wrapping entries in a root element.
 */
public class XMLFileLogRepository implements LogRepository {
    private static final String XML_DECLARATION = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
    private static final String ROOT_OPEN = "<log>\n";
    private static final String ROOT_CLOSE = "</log>\n";
    private final String path;

    /**
     * Constructs an XMLFileLogRepository for the given file path.
     *
     * @param path Path to the XML log file.
     */
    public XMLFileLogRepository(String path) {
        this.path = path;
        ensureRoot();
    }

    /**
     * Ensures the XML file exists and has a single root element.
     */
    private void ensureRoot() {
        File file = new File(path);
        if (!file.exists()) {
            try (FileWriter fw = new FileWriter(path)) {
                fw.write(XML_DECLARATION + ROOT_OPEN + ROOT_CLOSE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Writes the formatted message inside the XML root, adding it before the closing tag.
     *
     * @param formattedMessage The timestamped and leveled log message.
     */
    public void write(String formattedMessage) {
        try (RandomAccessFile raf = new RandomAccessFile(path, "rw")) {
            byte[] closeBytes = ROOT_CLOSE.getBytes(StandardCharsets.UTF_8);
            long insertPos = raf.length() - closeBytes.length;
            raf.seek(insertPos);
            String entry = "  <message>" + escapeXml(formattedMessage) + "</message>\n";
            raf.write(entry.getBytes(StandardCharsets.UTF_8));
            raf.write(closeBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Escapes special XML characters in a string.
     *
     * @param s Raw string to escape.
     * @return Escaped XML-safe string.
     */
    private String escapeXml(String s) {
        return s.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&apos;");
    }
}
