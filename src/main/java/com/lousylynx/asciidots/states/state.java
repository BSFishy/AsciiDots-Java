package com.lousylynx.asciidots.states;

interface State {
    void on_tick(Dot wrapper);
}
