package co.com.pragma.backend_challenge.plaza.domain.exception;

import co.com.pragma.backend_challenge.plaza.domain.util.DomainConstants;

public class DishDoesNotBelongToOrderRestaurantException extends RuntimeException {

    public DishDoesNotBelongToOrderRestaurantException(String dishName, String restaurantName) {
        super(String.format(
                DomainConstants.DISH_DOES_NOT_BELONG_TO_RESTAURANT,
                dishName, restaurantName
        ));
    }
}
