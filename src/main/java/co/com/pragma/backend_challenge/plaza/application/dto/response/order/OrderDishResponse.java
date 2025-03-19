package co.com.pragma.backend_challenge.plaza.application.dto.response.order;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDishResponse {
    Long dishId;
    Integer quantity;
}
