package co.com.pragma.backend_challenge.plaza.application.handler;

import co.com.pragma.backend_challenge.plaza.application.dto.request.order.OrderRequest;
import co.com.pragma.backend_challenge.plaza.application.dto.response.order.OrderCreatedResponse;

public interface OrderHandler {
    OrderCreatedResponse createOrder(OrderRequest orderRequest);
}
