package co.com.pragma.backend_challenge.plaza.application.dto.request.order;

import co.com.pragma.backend_challenge.plaza.application.util.AppConstants;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderRequest {
    @NotNull(message = AppConstants.RESTAURANT_ID_FIELD_NOT_NULL)
    private String restaurantId;

    @Min(value = AppConstants.MIN_ORDER_DISHES, message = AppConstants.AT_LEAST_ONE_DISH_IN_ORDER)
    private List<OrderDishRequest> dishes;
}
