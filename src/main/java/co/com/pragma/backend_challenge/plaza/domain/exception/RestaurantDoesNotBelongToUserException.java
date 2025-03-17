package co.com.pragma.backend_challenge.plaza.domain.exception;

import co.com.pragma.backend_challenge.plaza.domain.util.DomainConstants;

public class RestaurantDoesNotBelongToUserException extends RuntimeException {
    public RestaurantDoesNotBelongToUserException() {
        super(DomainConstants.RESTAURANT_DOES_NOT_BELONG_TO_USER);
    }
}
