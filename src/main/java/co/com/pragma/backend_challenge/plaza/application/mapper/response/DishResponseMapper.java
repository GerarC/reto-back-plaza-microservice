package co.com.pragma.backend_challenge.plaza.application.mapper.response;

import co.com.pragma.backend_challenge.plaza.application.dto.response.DishResponse;
import co.com.pragma.backend_challenge.plaza.application.dto.response.PageResponse;
import co.com.pragma.backend_challenge.plaza.domain.model.Dish;
import co.com.pragma.backend_challenge.plaza.domain.model.DishCategory;
import co.com.pragma.backend_challenge.plaza.domain.model.Restaurant;
import co.com.pragma.backend_challenge.plaza.domain.util.annotation.Generated;
import co.com.pragma.backend_challenge.plaza.domain.util.pagination.DomainPage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Generated
@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DishResponseMapper {
    default String toCategoryText(DishCategory category){
        return category.getDescription();
    }

    default String toRestaurantId(Restaurant restaurant){
        return restaurant.getId();
    }

    @Mapping(target = "restaurantId", source = "restaurant")
    DishResponse toResponse(Dish restaurant);
    List<DishResponse> toResponses(List<Dish> restaurants);
    PageResponse<DishResponse> toResponses(DomainPage<Dish> dishPage);
}
