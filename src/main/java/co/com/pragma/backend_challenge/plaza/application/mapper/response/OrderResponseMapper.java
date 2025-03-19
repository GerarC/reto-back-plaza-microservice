package co.com.pragma.backend_challenge.plaza.application.mapper.response;

import co.com.pragma.backend_challenge.plaza.application.dto.response.PageResponse;
import co.com.pragma.backend_challenge.plaza.application.dto.response.order.OrderCreatedResponse;
import co.com.pragma.backend_challenge.plaza.application.dto.response.order.OrderDishResponse;
import co.com.pragma.backend_challenge.plaza.domain.model.Dish;
import co.com.pragma.backend_challenge.plaza.domain.model.DishCategory;
import co.com.pragma.backend_challenge.plaza.domain.model.Restaurant;
import co.com.pragma.backend_challenge.plaza.domain.model.order.Order;
import co.com.pragma.backend_challenge.plaza.domain.model.order.OrderDish;
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
public interface OrderResponseMapper {
    default Long toDishId(Dish dish){
        return dish.getId();
    }
    default String toRestaurantId(Restaurant restaurant){
        return restaurant.getId();
    }

    @Mapping(target = "dishId", source = "dish")
    OrderDishResponse toResponse(OrderDish orderDish);

    @Mapping(target = "restaurantId", source = "restaurant")
    OrderCreatedResponse toCreatedResponse(Order restaurant);
    PageResponse<OrderCreatedResponse> toResponses(DomainPage<Order> restaurantsPage);
}
