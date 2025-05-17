package lk.ac.iit.asd.charindu;

public class ConsoleLogRepository implements LogRepository {
    public void write(String formattedMessage) {
        System.out.println(formattedMessage);
    }
}
