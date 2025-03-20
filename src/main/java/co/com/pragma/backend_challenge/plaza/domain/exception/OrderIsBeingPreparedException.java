package co.com.pragma.backend_challenge.plaza.domain.exception;

import co.com.pragma.backend_challenge.plaza.domain.util.DomainConstants;

public class OrderIsBeingPreparedException extends RuntimeException {

    public OrderIsBeingPreparedException(){
        super(DomainConstants.ORDER_CANNOT_BE_CANCELED);
    }
}
