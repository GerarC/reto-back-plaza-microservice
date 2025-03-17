package co.com.pragma.backend_challenge.plaza.domain.exception;

import co.com.pragma.backend_challenge.plaza.domain.util.DomainConstants;

public class NotAuthorizedException extends RuntimeException {
    public NotAuthorizedException() {
        super(DomainConstants.NOT_AUTHORIZED_ERROR_MESSAGE);
    }
}
