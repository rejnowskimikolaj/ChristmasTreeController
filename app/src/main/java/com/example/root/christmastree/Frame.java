package com.example.root.christmastree;

/**
 * Created by root on 12/15/17.
 */

public class Frame {
    int ledLevel;

    public Frame(int ledLevel) {
        this.ledLevel = ledLevel;
    }

    public int getLedLevel() {
        return ledLevel;
    }

    public void setLedLevel(int ledLevel) {
        this.ledLevel = ledLevel;
    }
}
