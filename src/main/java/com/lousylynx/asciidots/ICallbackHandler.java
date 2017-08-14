package com.lousylynx.asciidots;

public interface ICallbackHandler {
    public int getInput();
    public void onOutput(String value);
    
    public void onTick(String[][] world);
    
    public void onStart();
    public void onEnd();
}
