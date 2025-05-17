package lk.ac.iit.asd.charindu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Database implementation
public class DatabaseLogRepository implements LogRepository {
    private final Connection conn;

    public DatabaseLogRepository(String url) throws SQLException {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("H2 Driver not found", e);
        }
        conn = DriverManager.getConnection(url);
        conn.createStatement().execute(
                "CREATE TABLE IF NOT EXISTS logs (id IDENTITY, message VARCHAR)");
    }

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
