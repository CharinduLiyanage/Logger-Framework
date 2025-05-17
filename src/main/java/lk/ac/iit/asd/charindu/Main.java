package lk.ac.iit.asd.charindu;

import java.sql.*;


public class Main {
    public static void main(String[] args) throws SQLException {
        // Create repositories
        LogRepository consoleRepo = new ConsoleLogRepository();
        LogRepository fileRepo = new FileLogRepository("app.log");
        LogRepository xmlRepo = new XMLFileLogRepository("app.xml");
        LogRepository dbRepo = new DatabaseLogRepository("jdbc:h2:~/logs");

        // Run demo for each type
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


    private static void runDemo(String title, LogRepository repo) throws SQLException {
        System.out.println("=== " + title + " ===");
        Logger.getInstance().configure(LogLevel.INFORM, true, repo);

        // Create a new bank account for Alice with initial balance.
        BankAccount acct = new BankAccount("Alice", "12345", 1000);

        // Perform deposit and withdrawal operations.
        acct.deposit(500);
        acct.withdraw(200);
        // Attempt invalid withdrawal to demonstrate error logging.
        acct.withdraw(-50);
    }
}