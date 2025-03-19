package co.com.pragma.backend_challenge.plaza.domain.exception;

import co.com.pragma.backend_challenge.plaza.domain.util.DomainConstants;

public class CustomerAlreadyHasAProcessingOrderException extends RuntimeException {
    public CustomerAlreadyHasAProcessingOrderException() {
        super(DomainConstants.CUSTOMER_ALREADY_HAS_A_PROCESSING_ORDER_MESSAGE);
    }
}
