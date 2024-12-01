package org.example.main;

public class InvalidPokerBoardException extends RuntimeException {
    public InvalidPokerBoardException() {
    }

    public InvalidPokerBoardException(String message) {
        super(message);
    }

    public InvalidPokerBoardException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPokerBoardException(Throwable cause) {
        super(cause);
    }

    public InvalidPokerBoardException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}