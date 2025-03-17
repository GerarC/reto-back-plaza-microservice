package co.com.pragma.backend_challenge.plaza.domain.spi.persistence;

import co.com.pragma.backend_challenge.plaza.domain.model.Restaurant;

public interface RestaurantPersistencePort {
    Restaurant findById(String id);
    Restaurant saveRestaurant(Restaurant restaurant);
    Restaurant findByNit(String nit);
}
