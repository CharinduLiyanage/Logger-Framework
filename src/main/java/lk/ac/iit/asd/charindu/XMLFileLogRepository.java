package lk.ac.iit.asd.charindu;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

public class XMLFileLogRepository implements LogRepository {
    private static final String XML_DECLARATION = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
    private static final String ROOT_OPEN = "<log>\n";
    private static final String ROOT_CLOSE = "</log>\n";
    private final String path;

    public XMLFileLogRepository(String path) {
        this.path = path;
        File file = new File(path);
        if (!file.exists()) {
            try (FileWriter fw = new FileWriter(path)) {
                fw.write(XML_DECLARATION);
                fw.write(ROOT_OPEN);
                fw.write(ROOT_CLOSE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void write(String formattedMessage) {
        String escaped = escapeXml(formattedMessage);
        String entry = String.format("    <message>%s</message>\n", escaped);
        try (RandomAccessFile raf = new RandomAccessFile(path, "rw")) {
            byte[] closeBytes = ROOT_CLOSE.getBytes(StandardCharsets.UTF_8);
            long pos = raf.length() - closeBytes.length;
            if (pos < 0) pos = raf.length();
            raf.seek(pos);
            raf.write(entry.getBytes(StandardCharsets.UTF_8));
            raf.write(closeBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String escapeXml(String s) {
        return s.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&apos;");
    }
}
