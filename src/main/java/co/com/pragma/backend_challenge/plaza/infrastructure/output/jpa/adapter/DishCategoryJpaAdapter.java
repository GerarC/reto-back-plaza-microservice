package co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.adapter;

import co.com.pragma.backend_challenge.plaza.domain.model.DishCategory;
import co.com.pragma.backend_challenge.plaza.domain.spi.persistence.DishCategoryPersistecePort;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.entity.DishCategoryEntity;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.mapper.DishCategoryEntityMapper;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.repository.DishCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DishCategoryJpaAdapter implements DishCategoryPersistecePort {
    private final DishCategoryRepository dishCategoryRepository;
    private final DishCategoryEntityMapper dishCategoryEntityMapper;

    @Override
    public DishCategory findByDescription(String description) {
        return dishCategoryEntityMapper.toDomain(
                dishCategoryRepository.findByDescription(description).orElse(null)
        );
    }

    @Override
    public DishCategory saveCategory(String description) {
        return dishCategoryEntityMapper.toDomain(dishCategoryRepository.save(
                DishCategoryEntity.builder()
                        .description(description)
                        .build()
        ));
    }
}
