package com.lousylynx.asciidots.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.fusesource.jansi.AnsiConsole;

import static org.fusesource.jansi.Ansi.ansi;

public class Log {

    static {
        AnsiConsole.systemInstall();

        logger = LogManager.getLogger("asciidots");
    }

    private static final Logger logger;

    public static void info(Object message) {
        log(Level.INFO, message);
    }

    public static void error(Object message) {
        System.out.println(ansi().render("@|red " + message + "|@"));
    }

    public static void log(Level level, Object message, Throwable t) {
        logger.log(level, ansi().render("@|red " + message + "|@"), t);
    }

    public static void log(Level level, Object message) {
        logger.log(level, message);
    }
}
