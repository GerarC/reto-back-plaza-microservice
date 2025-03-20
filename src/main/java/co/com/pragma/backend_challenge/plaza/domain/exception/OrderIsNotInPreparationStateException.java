package co.com.pragma.backend_challenge.plaza.domain.exception;

import co.com.pragma.backend_challenge.plaza.domain.util.DomainConstants;

public class OrderIsNotInPreparationStateException extends RuntimeException {
    public OrderIsNotInPreparationStateException(){
        super(DomainConstants.ORDER_CANNOT_BE_SET_AS_DONE_BECAUSE_IS_NOT_BEING_PREPARED);
    }
}
