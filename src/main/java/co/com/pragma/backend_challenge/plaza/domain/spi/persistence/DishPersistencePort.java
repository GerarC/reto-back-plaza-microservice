package co.com.pragma.backend_challenge.plaza.domain.spi.persistence;

import co.com.pragma.backend_challenge.plaza.domain.model.Dish;

public interface DishPersistencePort {
    Dish saveDish(Dish dish);
    Dish findById(Long id);
}
