package co.com.pragma.backend_challenge.plaza.domain.exception;

import co.com.pragma.backend_challenge.plaza.domain.util.DomainConstants;

public class OrderIsNotDoneException extends RuntimeException {

    public OrderIsNotDoneException(){
        super(DomainConstants.ORDER_IS_NOT_DONE);
    }
}
