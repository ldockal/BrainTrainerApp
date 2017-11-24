package com.lukas_tamz.braintrainer.exceptions;

/**
 * Created by ldockal on 11/17/2017.
 */

public final class GamesNotFoundException extends RuntimeException {

    public GamesNotFoundException() {
    }

    public GamesNotFoundException(String message) {
        super(message);
    }

    public GamesNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public GamesNotFoundException(Throwable cause) {
        super(cause);
    }

    public GamesNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
