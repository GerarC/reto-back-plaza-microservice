package co.com.pragma.backend_challenge.plaza.domain.usecase;

import co.com.pragma.backend_challenge.plaza.domain.api.DishServicePort;
import co.com.pragma.backend_challenge.plaza.domain.exception.EntityNotFoundException;
import co.com.pragma.backend_challenge.plaza.domain.exception.RestaurantDoesNotBelongToUserException;
import co.com.pragma.backend_challenge.plaza.domain.model.Dish;
import co.com.pragma.backend_challenge.plaza.domain.model.DishCategory;
import co.com.pragma.backend_challenge.plaza.domain.model.Restaurant;
import co.com.pragma.backend_challenge.plaza.domain.model.security.AuthorizedUser;
import co.com.pragma.backend_challenge.plaza.domain.spi.persistence.DishCategoryPersistecePort;
import co.com.pragma.backend_challenge.plaza.domain.spi.persistence.DishPersistencePort;
import co.com.pragma.backend_challenge.plaza.domain.spi.persistence.RestaurantPersistencePort;
import co.com.pragma.backend_challenge.plaza.domain.spi.security.AuthorizationSecurityPort;
import co.com.pragma.backend_challenge.plaza.domain.util.TokenHolder;
import co.com.pragma.backend_challenge.plaza.domain.util.enums.DishState;

import java.util.Objects;

public class DishUseCase implements DishServicePort {
    private final DishPersistencePort dishPersistencePort;
    private final DishCategoryPersistecePort dishCategoryPersistecePort;
    private final RestaurantPersistencePort restaurantPersistencePort;
    private final AuthorizationSecurityPort authorizationSecurityPort;

    public DishUseCase(DishPersistencePort dishPersistencePort,
                       DishCategoryPersistecePort dishCategoryPersistecePort,
                       RestaurantPersistencePort restaurantPersistencePort,
                       AuthorizationSecurityPort authorizationSecurityPort) {
        this.dishPersistencePort = dishPersistencePort;
        this.dishCategoryPersistecePort = dishCategoryPersistecePort;
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.authorizationSecurityPort = authorizationSecurityPort;
    }

    @Override
    public Dish createDish(Dish dish) {
        validateDish(dish);
        dish.setCategory(
                findOrCreateCategory(dish.getCategory().getDescription())
        );
        dish.setState(DishState.ACTIVE);
        return dishPersistencePort.saveDish(dish);
    }

    @Override
    public Dish modifyDish(Long id, Dish dish) {
        Dish dishToModify = dishPersistencePort.findById(id);
        if(dishToModify == null)
            throw new EntityNotFoundException(Dish.class.getSimpleName(), id.toString());
        validateDish(dishToModify);
        if(dish.getDescription() != null) dishToModify.setDescription(dish.getDescription());
        if(dish.getPrice() != null) dishToModify.setPrice(dish.getPrice());
        return dishPersistencePort.saveDish(dishToModify);
    }

    private DishCategory findOrCreateCategory(String description) {
        DishCategory category = dishCategoryPersistecePort.findByDescription(description);
        if (category == null) category = dishCategoryPersistecePort.saveCategory(description);
        return category;
    }

    private void validateDish(Dish dish) {
        Restaurant restaurant = restaurantPersistencePort.findById(dish.getRestaurant().getId());
        AuthorizedUser user = authorizationSecurityPort.authorize(TokenHolder.getToken());
        if (restaurant == null)
            throw new EntityNotFoundException(Restaurant.class.getSimpleName(), dish.getRestaurant().getId());
        if(!Objects.equals(user.getId(), restaurant.getOwnerId()))
            throw new RestaurantDoesNotBelongToUserException();
    }
}
