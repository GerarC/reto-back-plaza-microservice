package co.com.pragma.backend_challenge.plaza.domain.exception;

import co.com.pragma.backend_challenge.plaza.domain.util.DomainConstants;

public class OrderDoesNotBelongToTheCustomerException extends RuntimeException {

    public OrderDoesNotBelongToTheCustomerException(){
        super(DomainConstants.ORDER_DOES_NOT_BELONG_TO_THE_USER);
    }
}
