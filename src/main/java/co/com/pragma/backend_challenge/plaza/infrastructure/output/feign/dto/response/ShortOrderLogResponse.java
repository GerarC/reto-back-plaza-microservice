package co.com.pragma.backend_challenge.plaza.infrastructure.output.feign.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShortOrderLogResponse {
    private Long orderId;
    private String customerId;
    private String restaurantId;
}
