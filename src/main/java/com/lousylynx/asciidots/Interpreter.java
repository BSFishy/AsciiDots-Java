package com.lousylynx.asciidots;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.file.Paths;

public class Interpreter {

    public static void main(String[] args) throws IOException {
        System.out.println();
//        Interpreter interpreter = new Interpreter(".-$#", ".");
    }

    public Interpreter(String program, String programDir, ICallbackHandler callbackHandler) throws IOException {
        String path = Paths.get(".").toAbsolutePath().normalize().toString();
        String pathToPython = "/usr/local/bin/python3";

        Process process = Runtime.getRuntime().exec(new String[]{
                pathToPython,
                path + "/interpreter.py"
        });

        Reader stdOut = new InputStreamReader(process.getInputStream(), "UTF-8");
        OutputStream stdIn = process.getOutputStream();


        char[] passRequest = new char[1024];
        int len = 0;
        while (true) {
            int charsRead = stdOut.read(passRequest, len, passRequest.length - len);

            if (charsRead == -1) {
                break;
            }

            len += charsRead;

            if (new String(passRequest, len - charsRead, len).contains("?: ")) {
                stdIn.write("6\n".getBytes("utf-8"));
                stdIn.flush();
            } else if (new String(passRequest, len - charsRead, len).contains("$ ")) {
                stdIn.write((program + "\n%EOF\n").getBytes("utf-8"));
                stdIn.flush();
            } else {
                System.out.print(new String(passRequest, len - charsRead, len));
            }

            if (len > 512) {
                passRequest = new char[1024];
                len = 0;
            }
        }

        process.destroy();
    }
}
