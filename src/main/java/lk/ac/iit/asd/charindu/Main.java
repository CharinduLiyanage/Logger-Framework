package lk.ac.iit.asd.charindu;

// Example Main for Q1
public class Main {
    public static void main(String[] args) {
        // Configure logger: show all messages and enable logging
        Logger.getInstance().configure(LogLevel.INFORM);

        BankAccount acct = new BankAccount("Alice", "12345", 1000);
        acct.deposit(500);
        acct.withdraw(200);
        acct.withdraw(-50);
    }
}
