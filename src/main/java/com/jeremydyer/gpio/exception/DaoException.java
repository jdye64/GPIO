package com.jeremydyer.gpio.exception;

/**
 * User: Jeremy Dyer
 * Date: 1/14/14
 * Time: 3:18 PM
 */
public class DaoException extends RuntimeException {

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }

}
