package co.com.pragma.backend_challenge.plaza.domain.usecase;

import co.com.pragma.backend_challenge.plaza.domain.api.DishServicePort;
import co.com.pragma.backend_challenge.plaza.domain.exception.EntityNotFoundException;
import co.com.pragma.backend_challenge.plaza.domain.model.Dish;
import co.com.pragma.backend_challenge.plaza.domain.model.DishCategory;
import co.com.pragma.backend_challenge.plaza.domain.model.Restaurant;
import co.com.pragma.backend_challenge.plaza.domain.spi.persistence.DishCategoryPersistecePort;
import co.com.pragma.backend_challenge.plaza.domain.spi.persistence.DishPersistencePort;
import co.com.pragma.backend_challenge.plaza.domain.spi.persistence.RestaurantPersistencePort;
import co.com.pragma.backend_challenge.plaza.domain.util.enums.DishState;

public class DishUseCase implements DishServicePort {
    private final DishPersistencePort dishPersistencePort;
    private final DishCategoryPersistecePort dishCategoryPersistecePort;
    private final RestaurantPersistencePort restaurantPersistencePort;

    public DishUseCase(DishPersistencePort dishPersistencePort, DishCategoryPersistecePort dishCategoryPersistecePort, RestaurantPersistencePort restaurantPersistencePort) {
        this.dishPersistencePort = dishPersistencePort;
        this.dishCategoryPersistecePort = dishCategoryPersistecePort;
        this.restaurantPersistencePort = restaurantPersistencePort;
    }

    @Override
    public Dish createDish(Dish dish) {
        // Todo: Only the Owner of a restaurant should be able to create a dish on that restaurant.
        validateDish(dish);
        dish.setCategory(
                findOrCreateCategory(dish.getCategory().getDescription())
        );
        dish.setState(DishState.ACTIVE);
        return dishPersistencePort.saveDish(dish);
    }

    @Override
    public Dish modifyDish(Long id, Dish dish) {
        // Todo: Only the Owner of a restaurant should be able to modify a dish on that restaurant.
        Dish dishToModify = dishPersistencePort.findById(id);
        if(dishToModify == null)
            throw new EntityNotFoundException(Dish.class.getSimpleName(), id.toString());
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
        if (restaurant == null)
            throw new EntityNotFoundException(Restaurant.class.getSimpleName(), dish.getRestaurant().getId());
    }
}
