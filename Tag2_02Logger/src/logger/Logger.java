package logger;

import appender.Appender;

public class Logger {

    private final Appender appender;

    public Logger(Appender appender) {
        this.appender = appender;
    }

    public void log(String message) {
        final String komplexerPrefix = "Prefix"; // Hier steht komplexer Code
        appender.write(komplexerPrefix + message);
    }
}
