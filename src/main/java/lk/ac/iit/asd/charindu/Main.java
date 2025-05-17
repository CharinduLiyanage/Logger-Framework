package lk.ac.iit.asd.charindu;

import java.sql.*;


/**
 * Demonstrates the Logger framework across all available backends.
 * <p>
 * For each repository type, we configure the singleton Logger
 * and perform a small sequence of BankAccount operations
 * to show log output in that backend.
 * </p>
 */
public class Main {
    /**
     * Application entry point.
     *
     * @param args Command-line arguments (unused).
     * @throws SQLException if any database repository initialization fails.
     */
    public static void main(String[] args) throws SQLException {
        // Instantiate each repository directly (could also use LogRepositoryFactory)
        LogRepository consoleRepo = new ConsoleLogRepository();
        LogRepository fileRepo = new FileLogRepository("app.log");
        LogRepository xmlRepo = new XMLFileLogRepository("app.xml");
        LogRepository dbRepo = new DatabaseLogRepository("jdbc:h2:~/logs");

        // Execute the same demo on each backend
        runDemo("Console Logging", consoleRepo);
        runDemo("File Logging", fileRepo);
        runDemo("XML Logging", xmlRepo);
        runDemo("Database Logging", dbRepo);

        // Read back DB logs
        System.out.println("=== Database Log Entries ===");
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try (Connection conn = DriverManager.getConnection("jdbc:h2:~/logs");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, message FROM logs ORDER BY id")) {
            while (rs.next()) {
                System.out.println(rs.getInt("id") + ": " + rs.getString("message"));
            }
        }
    }

    /**
     * Runs a mini‐demo of BankAccount operations, logging each action.
     *
     * @param title A human-readable title printed before the demo.
     * @param repo  The backend to which log messages will be routed.
     * @throws SQLException if the repo is a {@link DatabaseLogRepository}
     *                      and its underlying connection/setup fails.
     */
    private static void runDemo(String title, LogRepository repo)
            throws SQLException {
        System.out.println("=== " + title + " ===");
        // Configure logger: only INFORM and above, enabled, using this repo
        Logger.getInstance().configure(LogLevel.INFORM, true, repo);

        // Create a new bank account for Alice with an initial balance
        BankAccount acct = new BankAccount("Alice", "12345", 1000);

        // Perform deposit and withdrawal operations
        acct.deposit(500);
        acct.withdraw(200);
        // Attempt invalid withdrawal to demonstrate error logging
        acct.withdraw(-50);
    }
}