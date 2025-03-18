package co.com.pragma.backend_challenge.plaza.application.handler;

import co.com.pragma.backend_challenge.plaza.application.dto.request.DishRequest;
import co.com.pragma.backend_challenge.plaza.application.dto.request.DishStateRequest;
import co.com.pragma.backend_challenge.plaza.application.dto.request.PatchDishRequest;
import co.com.pragma.backend_challenge.plaza.application.dto.response.DishResponse;
import co.com.pragma.backend_challenge.plaza.domain.util.enums.DishState;

public interface DishHandler {
    DishResponse createDish(DishRequest dishRequest);
    DishResponse modifyDish(Long id, PatchDishRequest patchDishRequest);
    DishResponse setDishState(Long id, DishStateRequest dishStateRequest);
}
