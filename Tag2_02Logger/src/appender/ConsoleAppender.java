package appender;

public class ConsoleAppender implements Appender{
    @Override
    public void write(String message) {
        System.out.println(message);
    }
}
