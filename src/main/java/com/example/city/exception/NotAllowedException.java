package com.example.city.exception;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;

/**
 * This exception is thrown in case of a not activated user trying to authenticate.
 */
public class NotAllowedException extends AccessDeniedException {

    private static final long serialVersionUID = 1L;

    public NotAllowedException(String message) {
        super(message);
    }

    public NotAllowedException(String message, Throwable t) {
        super(message, t);
    }
}
