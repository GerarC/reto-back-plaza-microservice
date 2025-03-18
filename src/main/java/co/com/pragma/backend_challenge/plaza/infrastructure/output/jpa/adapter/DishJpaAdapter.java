package co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.adapter;

import co.com.pragma.backend_challenge.plaza.domain.model.Dish;
import co.com.pragma.backend_challenge.plaza.domain.spi.persistence.DishPersistencePort;
import co.com.pragma.backend_challenge.plaza.domain.util.filter.DishFilter;
import co.com.pragma.backend_challenge.plaza.domain.util.pagination.DomainPage;
import co.com.pragma.backend_challenge.plaza.domain.util.pagination.PaginationData;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.entity.DishEntity;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.mapper.DishEntityMapper;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.mapper.PaginationJpaMapper;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.repository.DishRepository;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.specification.DishSpecificationBuilder;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.util.PaginationJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DishJpaAdapter implements DishPersistencePort {
    private final DishRepository dishRepository;
    private final DishEntityMapper dishEntityMapper;
    private final PaginationJpaMapper paginationJpaMapper;

    @Override
    public Dish saveDish(Dish dish) {
        DishEntity entity = dishEntityMapper.toEntity(dish);
        return dishEntityMapper.toDomain(
                dishRepository.save(entity)
        );
    }

    @Override
    public Dish findById(Long id) {
        return dishEntityMapper.toDomain(
                dishRepository.findById(id).orElse(null)
        );
    }

    @Override
    public DomainPage<Dish> findAll(DishFilter filter, PaginationData paginationData) {
        Specification<DishEntity> specs = DishSpecificationBuilder.filterBy(filter);
        PaginationJpa paginationJpa = paginationJpaMapper.toJpa(paginationData);
        return dishEntityMapper.toDomains(
                dishRepository.findAll(specs, paginationJpa.createPageable())
        );
    }
}
