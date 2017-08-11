package com.lousylynx.asciidots.cli;

import lombok.Data;
import org.apache.commons.cli.*;

@Data
public class ADCLI {

    private boolean exit = false;
    private String[] args;
    private String code;

    private int ticks;
    private boolean silent;
    private boolean debug;
    private boolean compatDebug;
    private int debugLines;
    private float autosetpDebug;
    private int head;

    public static void main(String[] args) {
        new ADCLI(args).start();
    }

    public ADCLI(String[] args) {
        this.args = args;
    }

    public void start() {
        handleArguments(args);

        if (exit)
            System.exit(0);
    }

    private void handleArguments(String[] args) {
        Options options = new Options();

        addOptions(options);

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

            code = getFile(cmd);

            ticks = (int) getValue(cmd, "ticks", -1);
            silent = (boolean) getValue(cmd, "silent", false);
            debug = (boolean) getValue(cmd, "debug", false);
            compatDebug = (boolean) getValue(cmd, "compat_debug", false);
            debugLines = (int) getValue(cmd, "debug_lines", -1);
            autosetpDebug = (float) getValue(cmd, "autostep_debug", -1);
            head = (int) getValue(cmd, "head", -1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void addOptions(final Options options) {
        options.addOption(Option.builder(null)
                .longOpt("help")
                .hasArg(false)
                .desc("Display the help information")
                .build());
        options.addOption(Option.builder("v")
                .longOpt("version")
                .hasArg(false)
                .desc("Print the current version of AsciiDots")
                .build());
        options.addOption(Option.builder("t")
                .longOpt("ticks")
                .hasArg(true)
                .type(Integer.class)
                .optionalArg(false)
                .argName("ticks")
                .desc("Run the program for a specified number of ticks")
                .build());
        options.addOption(Option.builder("s")
                .longOpt("silent")
                .hasArg(false)
                .desc("Run without printing ANYTHING to the console. Useful for benchmarking")
                .build());
        options.addOption(Option.builder("d")
                .longOpt("debug")
                .hasArg(false)
                .desc("Run the program in debug mode. It shows the program and highlights the dots with red. Press enter to step the program once.")
                .build());
        options.addOption(Option.builder("w")
                .longOpt("compat_debug")
                .hasArg(false)
                .desc("Run the program without using ncurses. This can fix problems related to Windows.")
                .build());
        options.addOption(Option.builder("l")
                .longOpt("debug_lines")
                .hasArg(true)
                .type(Integer.class)
                .optionalArg(false)
                .argName("line-count")
                .desc("When not in compatibility mode, reserve the specified number of the lines for displaying the program")
                .build());
        options.addOption(Option.builder("a")
                .longOpt("autostep_debug")
                .hasArg(true)
                .type(Float.class)
                .optionalArg(false)
                .argName("delay")
                .desc("Step the program automatically, using the specified delay in seconds. Decimal numbers are permitted, and so is 0.")
                .build());
        options.addOption(Option.builder("h")
                .longOpt("head")
                .hasArg(true)
                .type(Integer.class)
                .optionalArg(false)
                .argName("x")
                .desc("Stop the program after x outputs")
                .build());
    }

    private Object getValue(CommandLine cmd, String option, Object defaultValue) {
        if(!cmd.hasOption(option))
            return defaultValue;

        try {
            return cmd.getParsedOptionValue(option);
        } catch (ParseException e) {
            System.out.println("The value for the argument \"" + option + "\" must be a number");
            System.exit(1);
        }

        return defaultValue;
    }

    private String getFile(CommandLine cmd) {
        String file = cmd.getArgs()[0];

        // TODO: continue reading the file and output the contents

        return file;
    }
}
