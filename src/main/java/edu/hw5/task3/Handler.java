package edu.hw5.task3;

import java.time.LocalDate;

abstract class Handler {
    protected Handler nextHandler;

    Handler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract LocalDate handleRequest(String request);
}
