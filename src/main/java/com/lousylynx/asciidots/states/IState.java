package com.lousylynx.asciidots.states;

import com.lousylynx.asciidots.Dot;

public interface IState {
    void on_tick(Dot wrapper);
}
