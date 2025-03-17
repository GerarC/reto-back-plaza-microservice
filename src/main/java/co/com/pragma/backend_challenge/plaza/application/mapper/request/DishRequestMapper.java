package co.com.pragma.backend_challenge.plaza.application.mapper.request;

import co.com.pragma.backend_challenge.plaza.application.dto.request.DishRequest;
import co.com.pragma.backend_challenge.plaza.domain.model.Dish;
import co.com.pragma.backend_challenge.plaza.domain.model.DishCategory;
import co.com.pragma.backend_challenge.plaza.domain.model.Restaurant;
import co.com.pragma.backend_challenge.plaza.domain.util.annotation.Generated;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Generated
@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DishRequestMapper {
    default DishCategory toCategory(String category){
        return DishCategory.builder()
                .description(category)
                .build();
    }
    default Restaurant toRestaurant(String restaurantId){
        return Restaurant.builder()
                .id(restaurantId)
                .build();
    }
    @Mapping(target = "restaurant", source = "restaurantId")
    Dish toDomain(DishRequest request);
    List<Dish> toDomains(List<DishRequest> requests);
}
