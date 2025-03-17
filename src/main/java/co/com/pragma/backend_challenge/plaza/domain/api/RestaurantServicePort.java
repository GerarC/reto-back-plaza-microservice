package co.com.pragma.backend_challenge.plaza.domain.api;

import co.com.pragma.backend_challenge.plaza.domain.model.Restaurant;

public interface RestaurantServicePort {
    Restaurant createRestaurant(Restaurant restaurant);
}
