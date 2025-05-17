package lk.ac.iit.asd.charindu;

/**
 * Represents a customer's bank account, supporting deposits, withdrawals, and balance inquiries.
 * Logs all operations using the Logger singleton.
 */
public class BankAccount {
    /**
     * Customer's full name.
     */
    private final String customerName;
    /**
     * Unique account number for the bank account.
     */
    private final String accountNumber;
    /**
     * Logger instance for recording account events.
     */
    private final Logger logger = Logger.getInstance();
    /**
     * Current account balance.
     */
    private double balance;

    /**
     * Constructs a new BankAccount with an initial balance.
     *
     * @param name   Customer's name.
     * @param accNum Account number as a string.
     * @param init   Initial balance amount.
     */
    public BankAccount(String name, String accNum, double init) {
        this.customerName = name;
        this.accountNumber = accNum;
        this.balance = init;
        // Log account creation event.
        logger.inform("[" + accountNumber + "] Account created for " + customerName);
    }

    /**
     * Deposits a specified amount into the account.
     *
     * @param amt Amount to deposit; must be positive.
     */
    public void deposit(double amt) {
        if (amt <= 0) {
            // Log invalid deposit attempt.
            logger.error("[" + accountNumber + "] Deposit amount must be positive");
            return;
        }
        balance += amt; // Increase balance.
        // Log successful deposit with new balance.
        logger.debug("[" + accountNumber + "] Deposited " + amt + "; new balance=" + balance);
    }

    /**
     * Withdraws a specified amount from the account if sufficient funds are available.
     *
     * @param amt Amount to withdraw; must be positive and <= current balance.
     */
    public void withdraw(double amt) {
        if (amt <= 0) {
            // Log an invalid withdrawal attempt.
            logger.error("[" + accountNumber + "] Withdrawal amount must be positive");
            return;
        }
        if (amt > balance) {
            // Log overdraft attempt.
            logger.error("[" + accountNumber + "] Insufficient funds");
            return;
        }
        balance -= amt; // Decrease balance.
        // Log successful withdrawal with new balance.
        logger.debug("[" + accountNumber + "] Withdrew " + amt + "; new balance=" + balance);
    }

    /**
     * Retrieves the current balance of the account.
     *
     * @return Current balance as double.
     */
    public double getBalance() {
        // Log balance inquiry.
        logger.debug("[" + accountNumber + "] Balance checked: " + balance);
        return balance;
    }
}