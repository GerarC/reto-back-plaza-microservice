package co.com.pragma.backend_challenge.plaza.domain.exception;

import co.com.pragma.backend_challenge.plaza.domain.util.DomainConstants;

public class SecurityPinDoesNotMatchException extends RuntimeException {

    public SecurityPinDoesNotMatchException(){
        super(DomainConstants.SECURITY_PIN_DOES_NOT_MATCH);
    }
}
