package com.lousylynx.asciidots;

public interface ICallbackHandler {
    int getInput();

    void onOutput(String value);

    void onTick(String[][] world);

    void onStart();

    void onEnd();
}
