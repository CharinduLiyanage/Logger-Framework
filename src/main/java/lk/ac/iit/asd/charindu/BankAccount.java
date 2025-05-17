package lk.ac.iit.asd.charindu;

// BankAccount using Logger
public class BankAccount {
    private final String customerName;
    private final String accountNumber;
    private final Logger logger = Logger.getInstance();
    private double balance;

    public BankAccount(String name, String accNum, double init) {
        this.customerName = name;
        this.accountNumber = accNum;
        this.balance = init;
        logger.inform("[" + accountNumber + "] Account created for " + customerName);
    }

    public void deposit(double amt) {
        if (amt <= 0) {
            logger.error("[" + accountNumber + "] Deposit amount must be positive");
            return;
        }
        balance += amt;
        logger.debug("[" + accountNumber + "] Deposited " + amt + "; new balance=" + balance);
    }

    public void withdraw(double amt) {
        if (amt <= 0) {
            logger.error("[" + accountNumber + "] Withdrawal amount is negative");
            return;
        }
        if (amt > balance) {
            logger.error("[" + accountNumber + "] Insufficient funds");
            return;
        }
        balance -= amt;
        logger.debug("[" + accountNumber + "] Withdrew " + amt + "; new balance=" + balance);
    }

    public double getBalance() {
        logger.debug("[" + accountNumber + "] Balance checked: " + balance);
        return balance;
    }
}