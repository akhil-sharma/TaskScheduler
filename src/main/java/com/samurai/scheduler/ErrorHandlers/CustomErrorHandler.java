package com.samurai.scheduler.ErrorHandlers;

import org.springframework.util.ErrorHandler;

public class CustomErrorHandler implements ErrorHandler {

    @Override
    public void handleError(Throwable t) {
        System.err.println("Error occurred in scheduling task: " + t.getMessage() );
        t.printStackTrace();
    }
}
