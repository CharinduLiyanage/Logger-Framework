package lk.ac.iit.asd.charindu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Logs messages to a relational database table.
 */
public class DatabaseLogRepository implements LogRepository {
    private final Connection conn;

    /**
     * Constructs a DatabaseLogRepository connecting to the given JDBC URL and initializing schema.
     *
     * @param url JDBC connection URL.
     * @throws SQLException If connection or schema setup fails.
     */
    public DatabaseLogRepository(String url) throws SQLException {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("H2 Driver not found", e);
        }
        this.conn = DriverManager.getConnection(url);
        conn.createStatement().execute(
                "CREATE TABLE IF NOT EXISTS logs (id IDENTITY, message VARCHAR)");
    }

    /**
     * Inserts the formatted message into the database table.
     *
     * @param formattedMessage The timestamped and leveled log message.
     */
    public void write(String formattedMessage) {
        try (PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO logs(message) VALUES(?)")) {
            ps.setString(1, formattedMessage);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
