package co.com.pragma.backend_challenge.plaza.domain.exception;

import co.com.pragma.backend_challenge.plaza.domain.util.DomainConstants;

public class OwnerHasNotRestaurantException extends RuntimeException {

    public OwnerHasNotRestaurantException(){
        super(DomainConstants.OWNER_HAS_NOT_RESTAURANT_REGISTERED);
    }
}
