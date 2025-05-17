package lk.ac.iit.asd.charindu;

/**
 * Example Main class demonstrating Logger and BankAccount usage.
 */
public class Main {
    /**
     * Entry point: configures the logger, creates an account, and performs transactions.
     *
     * @param args Command-line arguments (unused).
     */
    public static void main(String[] args) {
        // Configure logger to show messages of INFORM level and above.
        Logger.getInstance().configure(LogLevel.INFORM);

        // Create a new bank account for Alice with initial balance.
        BankAccount acct = new BankAccount("Alice", "12345", 1000);

        // Perform deposit and withdrawal operations.
        acct.deposit(500);
        acct.withdraw(200);
        // Attempt invalid withdrawal to demonstrate error logging.
        acct.withdraw(-50);
    }
}