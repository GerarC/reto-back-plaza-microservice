package co.com.pragma.backend_challenge.plaza.application.mapper.request.dish;

import co.com.pragma.backend_challenge.plaza.application.dto.request.dish.PatchDishRequest;
import co.com.pragma.backend_challenge.plaza.domain.model.Dish;
import co.com.pragma.backend_challenge.plaza.domain.util.annotation.Generated;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Generated
@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PatchDishRequestMapper {
    Dish toDomain(PatchDishRequest request);
    List<Dish> toDomains(List<PatchDishRequest> requests);
}
