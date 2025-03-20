package co.com.pragma.backend_challenge.plaza.application.dto.response.order;

import co.com.pragma.backend_challenge.plaza.domain.util.enums.OrderState;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderResponse {
    private Long id;
    private String customerId;
    private String restaurantId;
    private List<OrderDishResponse> dishes;
    private OrderState state;
    private String assignedEmployeeId;
}
