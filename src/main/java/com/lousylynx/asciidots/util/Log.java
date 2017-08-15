package com.lousylynx.asciidots.util;

import org.fusesource.jansi.AnsiConsole;

import static org.fusesource.jansi.Ansi.*;

public class Log {

    static {
        AnsiConsole.systemInstall();
    }

    public static void info(Object message) {
        System.out.println(message);
    }

    public static void error(String message) {
        System.out.println(ansi().render("@|red " + message + "|@"));
    }
}
