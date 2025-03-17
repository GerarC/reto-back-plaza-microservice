package co.com.pragma.backend_challenge.plaza.domain.usecase;

import co.com.pragma.backend_challenge.plaza.domain.api.RestaurantServicePort;
import co.com.pragma.backend_challenge.plaza.domain.exception.EntityAlreadyExistsException;
import co.com.pragma.backend_challenge.plaza.domain.exception.UserRoleMustBeOwnerException;
import co.com.pragma.backend_challenge.plaza.domain.model.Restaurant;
import co.com.pragma.backend_challenge.plaza.domain.spi.persistence.RestaurantPersistencePort;
import co.com.pragma.backend_challenge.plaza.domain.spi.persistence.UserPersistencePort;

public class RestaurantUseCase implements RestaurantServicePort {
    private final RestaurantPersistencePort restaurantPersistencePort;
    private final UserPersistencePort userPersistencePort;

    public RestaurantUseCase(RestaurantPersistencePort restaurantPersistencePort, UserPersistencePort userPersistencePort) {
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public Restaurant createRestaurant(Restaurant restaurant) {
        validateRestaurantData(restaurant);
        return restaurantPersistencePort.saveRestaurant(restaurant);
    }

    private void validateRestaurantData(Restaurant restaurant){
        if(!userPersistencePort.isOwner(restaurant.getOwnerId()))
            throw new UserRoleMustBeOwnerException();
        if(restaurantPersistencePort.findByNit(restaurant.getNit())!=null)
            throw new EntityAlreadyExistsException(Restaurant.class.getSimpleName(), restaurant.getNit());
    }
}
