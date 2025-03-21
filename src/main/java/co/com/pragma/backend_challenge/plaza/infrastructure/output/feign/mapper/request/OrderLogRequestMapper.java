package co.com.pragma.backend_challenge.plaza.infrastructure.output.feign.mapper.request;

import co.com.pragma.backend_challenge.plaza.domain.model.Restaurant;
import co.com.pragma.backend_challenge.plaza.domain.model.order.Order;
import co.com.pragma.backend_challenge.plaza.domain.util.annotation.Generated;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.feign.dto.request.NewOrderLogRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Generated
@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderLogRequestMapper {

    default String toRestaurantId(Restaurant restaurant){
        return restaurant.getId();
    }

    @Mapping(target = "restaurantId", source = "restaurant")
    @Mapping(target = "orderId", source = "id")
    NewOrderLogRequest toRequest(Order response);
}
