package co.com.pragma.backend_challenge.plaza.domain.api;

import co.com.pragma.backend_challenge.plaza.domain.model.Dish;

public interface DishServicePort {
    Dish createDish(Dish dish);
    Dish modifyDish(Long id, Dish dish);
}
