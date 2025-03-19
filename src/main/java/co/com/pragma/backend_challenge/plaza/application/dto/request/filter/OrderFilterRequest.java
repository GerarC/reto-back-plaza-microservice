package co.com.pragma.backend_challenge.plaza.application.dto.request.filter;

import co.com.pragma.backend_challenge.plaza.domain.util.enums.OrderState;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderFilterRequest {
    private List<OrderState> states;}
