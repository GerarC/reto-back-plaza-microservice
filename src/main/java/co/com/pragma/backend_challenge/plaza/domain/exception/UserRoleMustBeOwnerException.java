package co.com.pragma.backend_challenge.plaza.domain.exception;

import co.com.pragma.backend_challenge.plaza.domain.util.DomainConstants;

public class UserRoleMustBeOwnerException extends RuntimeException {
    public UserRoleMustBeOwnerException() {
        super(DomainConstants.RESTAURANT_OWNER_MUST_HAVE_OWNER_ROLE);
    }
}
