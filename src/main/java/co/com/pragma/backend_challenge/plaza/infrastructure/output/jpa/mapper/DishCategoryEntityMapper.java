package co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.mapper;

import co.com.pragma.backend_challenge.plaza.domain.model.DishCategory;
import co.com.pragma.backend_challenge.plaza.domain.util.annotation.Generated;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.entity.DishCategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Generated
@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DishCategoryEntityMapper {
    DishCategoryEntity toEntity(DishCategory dishCategory);
    List<DishCategoryEntity> toEntities(List<DishCategory> dishCategories);
    DishCategory toDomain(DishCategoryEntity entity);
    List<DishCategory> toDomains(List<DishCategoryEntity> categoryEntities);
}
