package me.igornostali.service.exception;

/**
 * A generic business exception
 * <p>
 * Created by igornostali on 4/27/2016.
 */
public class AlreadyExistsException extends Exception {

    public AlreadyExistsException(String message) {
        super((message));
    }
}
