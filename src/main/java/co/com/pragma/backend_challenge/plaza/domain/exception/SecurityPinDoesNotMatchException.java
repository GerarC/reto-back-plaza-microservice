package co.com.pragma.backend_challenge.plaza.domain.exception;

import co.com.pragma.backend_challenge.plaza.domain.util.DomainConstants;

public class SecurityPinDoesNotMatchException extends RuntimeException {

    public SecurityPinDoesNotMatchException(){
        super(DomainConstants.GIVEN_SECURITY_PIN_DOESN_T_MATCH_WITH_THIS_ORDER);
    }
}
