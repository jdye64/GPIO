package com.jeremydyer.gpio.exception;

/**
 * User: Jeremy Dyer
 * Date: 1/14/14
 * Time: 3:28 PM
 */
public class ObjectNotFoundException extends DaoException {

    public ObjectNotFoundException(String message) {
        super(message);
    }

    public ObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjectNotFoundException(Throwable cause) {
        super(cause);
    }

}
