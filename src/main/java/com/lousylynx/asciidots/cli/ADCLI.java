package com.lousylynx.asciidots.cli;

import org.apache.commons.cli.*;

public class ADCLI {

    private static boolean exit = false;

    public static void main(String[] args) {
        handleArguments(args);

        if (exit)
            System.exit(0);
    }

    private static void handleArguments(String[] args) {
        Options options = new Options();

        options.addOption(null, "help", false, "Print the help command");
        options.addOption("v", "version", false, "Prints the current version being ran");
        options.addOption("t", "ticks", false, "Run the program for a specified number of ticks");
        options.addOption("s", "silent", false, "Run without printing ANYTHING to the console. Useful for benchmarking");
        options.addOption("d", "debug", false, "Run the program in debug mode. It shows the program and highlights the dots with red. Press enter to step the program once.");
        options.addOption("w", "compat_debug", false, "Run the program without using ncurses. This can fix problems related to Windows.");
        options.addOption("l", "debug_lines", false, "When not in compatibility mode, reserve the specified number of the lines for displaying the program");
        options.addOption("a", "autostep_debug", false, "Step the program automatically, using the specified delay in seconds. Decimal numbers are permitted, and so is 0.");
        options.addOption("h", "head", false, "TODO: add description here");

        try {
            CommandLine cmd = new DefaultParser().parse(options, args, false);

            if (cmd.hasOption("help")) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("asciidots <FILE>", options, true);

                exit = true;
                return;
            }

            if (cmd.hasOption("version")) {
                // TODO: make this non hardcoded
                System.out.println("Current version is: 1.0");

                exit = true;
                return;
            }

            if (cmd.getArgs().length > 1) {
                System.out.println("Unknown stray arguments: " + String.join(", ", cmd.getArgList()));

                exit = true;
                return;
            } else if (cmd.getArgs().length < 1) {
                System.out.println("You must specify a file to run.");

                exit = true;
                return;
            }

            String program = getFile(cmd);

            // TODO: Run the interpreter from here
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static String getFile(CommandLine cmd) {
        String file = cmd.getArgs()[0];

        // TODO: continue reading the file and output the contents

        return file;
    }
}
