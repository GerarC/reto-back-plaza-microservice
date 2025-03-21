package co.com.pragma.backend_challenge.plaza.domain.spi.persistence;

import co.com.pragma.backend_challenge.plaza.domain.model.Restaurant;
import co.com.pragma.backend_challenge.plaza.domain.util.pagination.DomainPage;
import co.com.pragma.backend_challenge.plaza.domain.util.pagination.PaginationData;

public interface RestaurantPersistencePort {
    Restaurant findById(String id);
    Restaurant saveRestaurant(Restaurant restaurant);
    Restaurant findByNit(String nit);
    DomainPage<Restaurant> findAll(PaginationData paginationData);
    Restaurant findByOwnerId(String id);
}
