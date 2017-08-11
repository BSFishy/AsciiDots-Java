package com.lousylynx.asciidots;

import com.lousylynx.asciidots.states.Dead;
import com.lousylynx.asciidots.states.IState;

public class Dot {
  private IState currentState;

  public Dot() {
      currentState = new Dead();
  }

  public void set_state(IState s) {
      currentState = s;
  }

  public void on_tick() {
      currentState.on_tick(this);
  }
}
