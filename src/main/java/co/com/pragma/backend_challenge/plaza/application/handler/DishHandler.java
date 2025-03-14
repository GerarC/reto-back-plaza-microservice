package co.com.pragma.backend_challenge.plaza.application.handler;

import co.com.pragma.backend_challenge.plaza.application.dto.request.DishRequest;
import co.com.pragma.backend_challenge.plaza.application.dto.response.DishResponse;

public interface DishHandler {
    DishResponse createDish(DishRequest dishRequest);
}
