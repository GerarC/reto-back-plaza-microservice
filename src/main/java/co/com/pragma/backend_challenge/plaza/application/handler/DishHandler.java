package co.com.pragma.backend_challenge.plaza.application.handler;

import co.com.pragma.backend_challenge.plaza.application.dto.request.dish.DishRequest;
import co.com.pragma.backend_challenge.plaza.application.dto.request.dish.DishStateRequest;
import co.com.pragma.backend_challenge.plaza.application.dto.request.dish.PatchDishRequest;
import co.com.pragma.backend_challenge.plaza.application.dto.response.DishResponse;

public interface DishHandler {
    DishResponse createDish(DishRequest dishRequest);
    DishResponse modifyDish(Long id, PatchDishRequest patchDishRequest);
    DishResponse setDishState(Long id, DishStateRequest dishStateRequest);
}
