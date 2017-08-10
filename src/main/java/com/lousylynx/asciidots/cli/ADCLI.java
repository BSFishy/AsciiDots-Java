package com.lousylynx.asciidots.cli;

import org.apache.commons.cli.*;

public class ADCLI {

    private static boolean exit = false;

    public static void main(String[] args) {
        handleArguments(args);

        if(exit)
            System.exit(0);
    }

    private static void handleArguments(String[] args) {
        Options options = new Options();



//        options.addOption(Option.builder()
//                .longOpt("file")
//                .hasArg()
//                .build());

        options.addOption("file", true, FILE_OPTION);
        options.addOption("help", false, "Print the help command");
        options.addOption("t", "ticks", false, "Run the program for a specified number of ticks");
        options.addOption("s", "silent", false, "Run without printing ANYTHING to the console. Useful for benchmarking");
        options.addOption("d", "debug", false, "Run the program in debug mode. It shows the program and highlights the dots with red. Press enter to step the program once.");
        options.addOption("w", "compat_debug", false, "Run the program without using ncurses. This can fix problems related to Windows.");
        options.addOption("l", "debug_lines", false, "When not in compatibility mode, reserve the specified number of the lines for displaying the program");
        options.addOption("a", "autostep_debug", false, "Step the program automatically, using the specified delay in seconds. Decimal numbers are permitted, and so is 0.");
        options.addOption("h", "head", false, "TODO: add description here");

        try {
            CommandLine cmd = new DefaultParser().parse(options, args);

            if(cmd.hasOption("help")) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("asciidots", options);

                exit = true;
                return;
            }

            System.out.println(cmd.getOptionValue("filename"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
