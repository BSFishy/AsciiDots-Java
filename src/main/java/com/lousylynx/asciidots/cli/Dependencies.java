package com.lousylynx.asciidots.cli;

import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class Dependencies {

    public static void checkDependencies() {
        try {
            Class.forName("org.fusesource.jansi.Ansi");
        } catch (ClassNotFoundException ignored) {
            downloadDependency("jansi", "http://repo1.maven.org/maven2/org/fusesource/jansi/jansi/1.16/jansi-1.16.jar");

            refreshDependencies();
        }
    }

    public static void refreshDependencies() {
        File libsFolder = new File(getRunningDirectory() + "/libs/");
        JarFileLoader cl = new JarFileLoader(new URL[]{});

        boolean failed = false;

        try {
            cl.addUrl(new File(libsFolder.getAbsolutePath() + File.separator + "jansi.jar"));
            cl.loadClass("org.fusesource.jansi.Ansi");
            cl.loadClass("org.fusesource.jansi.AnsiConsole");

            Class.forName("org.fusesource.jansi.Ansi");
            Class.forName("org.fusesource.jansi.AnsiConsole");
        } catch (ClassNotFoundException | MalformedURLException e) {
            failed = true;
        }

        if (failed) {
            System.out.println("The libraries were installed, and are ready to run. Please run the command again to get the desired results.");

            System.exit(0);
        }
    }

    private static void downloadDependency(String filename, String url) {
        File libsFolder = new File(getRunningDirectory() + "/libs/");
        if (!libsFolder.exists())
            libsFolder.mkdir();

        try {
            System.out.println("Downloading dependency from: " + url);

            URL u = new URL(url);
            File outputFile = new File(libsFolder.getAbsolutePath() + File.separator + filename + ".jar");

            InputStream is = new BufferedInputStream(u.openStream());
            OutputStream os = new FileOutputStream(outputFile);
            OutputStream bos = new BufferedOutputStream(os, 1024);

            byte[] data = new byte[1024];
            int count;

            while ((count = is.read(data, 0, 1024)) != -1) {
                bos.write(data, 0, count);
            }

            os.flush();
            bos.flush();
            is.close();
            os.close();
            bos.close();

            System.out.println("Finished downloading dependency from: " + url);
        } catch (IOException e) {
            System.out.println("Unable to download Jansi.");
            e.printStackTrace();

            System.exit(1);
        }
    }

    private static String getRunningDirectory() {
        try {
            return FilenameUtils.getFullPath(
                    ADCLI.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
        } catch (URISyntaxException e) {
            e.printStackTrace();

            System.exit(1);
        }

        return null;
    }
}
