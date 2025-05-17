package lk.ac.iit.asd.charindu;

// Log levels with hierarchy
public enum LogLevel {
    ERROR(10), DEBUG(20), INFORM(30);
    private final int priority;

    LogLevel(int p) {
        priority = p;
    }

    public int getPriority() {
        return priority;
    }
}