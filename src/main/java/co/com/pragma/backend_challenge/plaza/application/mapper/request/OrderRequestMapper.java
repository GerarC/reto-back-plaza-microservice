package co.com.pragma.backend_challenge.plaza.application.mapper.request;

import co.com.pragma.backend_challenge.plaza.application.dto.request.order.OrderDishRequest;
import co.com.pragma.backend_challenge.plaza.application.dto.request.order.OrderRequest;
import co.com.pragma.backend_challenge.plaza.domain.model.Dish;
import co.com.pragma.backend_challenge.plaza.domain.model.Restaurant;
import co.com.pragma.backend_challenge.plaza.domain.model.order.Order;
import co.com.pragma.backend_challenge.plaza.domain.model.order.OrderDish;
import co.com.pragma.backend_challenge.plaza.domain.util.annotation.Generated;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Generated
@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderRequestMapper {

    String RESTAURANT = "restaurant";
    String RESTAURANT_ID = "restaurantId";
    String DISH = "dish";
    String DISH_ID = "dishId";

    default Restaurant toRestaurant(String restaurantId){
        return Restaurant.builder()
                .id(restaurantId)
                .build();
    }
    @Mapping(target = RESTAURANT, source = RESTAURANT_ID)
    Order toDomain(OrderRequest request);
    List<Order> toDomains(List<OrderRequest> requests);

    // OrderDishes
    default Dish toDish(Long dishId){
        return Dish.builder()
                .id(dishId)
                .build();
    }

    @Mapping(target = DISH, source = DISH_ID)
    OrderDish toDomain(OrderDishRequest orderDishRequest);
}
