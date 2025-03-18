package co.com.pragma.backend_challenge.plaza.domain.api;

import co.com.pragma.backend_challenge.plaza.domain.model.Dish;
import co.com.pragma.backend_challenge.plaza.domain.util.enums.DishState;

public interface DishServicePort {
    Dish createDish(Dish dish);
    Dish modifyDish(Long id, Dish dish);
    Dish changeDishState(Long id, DishState state);
}
