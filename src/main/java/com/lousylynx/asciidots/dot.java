package com.lousylynx.asciidots;

public class Dot {
  private State currentState;

  public Dot() {
      currentState = new Off();
  }

  public void set_state(State s) {
      currentState = s;
  }

  public void on_tick() {
      currentState.on_tick(this);
  }
}
