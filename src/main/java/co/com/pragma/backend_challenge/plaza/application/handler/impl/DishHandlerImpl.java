package co.com.pragma.backend_challenge.plaza.application.handler.impl;

import co.com.pragma.backend_challenge.plaza.application.dto.request.DishRequest;
import co.com.pragma.backend_challenge.plaza.application.dto.request.DishStateRequest;
import co.com.pragma.backend_challenge.plaza.application.dto.request.PatchDishRequest;
import co.com.pragma.backend_challenge.plaza.application.dto.response.DishResponse;
import co.com.pragma.backend_challenge.plaza.application.handler.DishHandler;
import co.com.pragma.backend_challenge.plaza.application.mapper.request.DishRequestMapper;
import co.com.pragma.backend_challenge.plaza.application.mapper.request.PatchDishRequestMapper;
import co.com.pragma.backend_challenge.plaza.application.mapper.response.DishResponseMapper;
import co.com.pragma.backend_challenge.plaza.domain.api.DishServicePort;
import co.com.pragma.backend_challenge.plaza.domain.model.Dish;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DishHandlerImpl implements DishHandler {
    private final DishServicePort dishServicePort;
    private final DishRequestMapper dishRequestMapper;
    private final DishResponseMapper dishResponseMapper;
    private final PatchDishRequestMapper patchDishRequestMapper;

    @Override
    public DishResponse createDish(DishRequest dishRequest) {
        Dish dish = dishRequestMapper.toDomain(dishRequest);
        return dishResponseMapper.toResponse(
                dishServicePort.createDish(dish)
        );
    }

    @Override
    public DishResponse modifyDish(Long id, PatchDishRequest patchDishRequest) {
        Dish dish = patchDishRequestMapper.toDomain(patchDishRequest);
        return dishResponseMapper.toResponse(
                dishServicePort.modifyDish(id, dish)
        );

    }

    @Override
    public DishResponse setDishState(Long id, DishStateRequest dishStateRequest) {
        return dishResponseMapper.toResponse(
                dishServicePort.changeDishState(id, dishStateRequest.getState())
        );
    }
}
