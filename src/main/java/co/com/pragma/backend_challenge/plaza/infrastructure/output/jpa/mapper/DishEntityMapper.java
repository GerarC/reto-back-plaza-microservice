package co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.mapper;

import co.com.pragma.backend_challenge.plaza.domain.model.Dish;
import co.com.pragma.backend_challenge.plaza.domain.util.annotation.Generated;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.entity.DishEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Generated
@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DishEntityMapper {
    DishEntity toEntity(Dish dish);
    List<DishEntity> toEntities(List<Dish> dishes);
    Dish toDomain(DishEntity entity);
    List<Dish> toDomains(List<DishEntity> dishEntities);
}
