package com.example.correctionapp.tool;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    private static final DateTimeFormatter TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final String className;

    public Logger(Class<?> clazz) {
        this.className = clazz.getSimpleName();
    }

    public void info(String message) {
        log("INFO", message);
    }

    public void warn(String message) {
        log("WARN", message);
    }

    public void error(String message) {
        log("ERROR", message);
    }

    public void error(String message, Exception e) {
        log("ERROR", message + " - " + e.getMessage());
        e.printStackTrace();
    }

    public void debug(String message) {
        log("DEBUG", message);
    }

    private void log(String level, String message) {
        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMAT);
        System.out.printf("[%s] %s - %s - %s%n", timestamp, level, className, message);
    }

    public static Logger getLogger(Class<?> clazz) {
        return new Logger(clazz);
    }
}
