package co.com.pragma.backend_challenge.plaza.application.dto.request.order;

import co.com.pragma.backend_challenge.plaza.application.util.AppConstants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDishRequest {
    @NotNull(message = AppConstants.DISH_ID_FIELD_NOT_NULL)
    Long dishId;

    @Positive(message = AppConstants.QUANTITY_MUST_BE_POSITIVE)
    @NotNull(message = AppConstants.QUANTITY_FIELD_NOT_NULL)
    Integer quantity;
}
