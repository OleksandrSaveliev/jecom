package com.tmdna.jecom.security.exception;

import org.springframework.security.core.AuthenticationException;

public class ApplicationAuthenticationException extends AuthenticationException {
    public ApplicationAuthenticationException(String message) {
        super(message);
    }
}
