package co.com.pragma.backend_challenge.plaza.domain.spi;

import co.com.pragma.backend_challenge.plaza.domain.model.Dish;

public interface DishPersistencePort {
    Dish saveDish(Dish dish);
}
