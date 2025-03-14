package co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.adapter;

import co.com.pragma.backend_challenge.plaza.domain.model.Dish;
import co.com.pragma.backend_challenge.plaza.domain.spi.DishPersistencePort;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.entity.DishEntity;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.mapper.DishEntityMapper;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.repository.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DishJpaAdapter implements DishPersistencePort {
    private final DishRepository dishRepository;
    private final DishEntityMapper dishEntityMapper;

    @Override
    public Dish saveDish(Dish dish) {
        DishEntity entity = dishEntityMapper.toEntity(dish);
        return dishEntityMapper.toDomain(
                dishRepository.save(entity)
        );
    }
}
