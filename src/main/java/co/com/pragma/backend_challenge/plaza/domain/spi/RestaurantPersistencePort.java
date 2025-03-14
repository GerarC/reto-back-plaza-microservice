package co.com.pragma.backend_challenge.plaza.domain.spi;

import co.com.pragma.backend_challenge.plaza.domain.model.Restaurant;

public interface RestaurantPersistencePort {
    Restaurant saveRestaurant(Restaurant restaurant);
    Restaurant findByNit(String nit);
}
