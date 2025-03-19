package co.com.pragma.backend_challenge.plaza.application.handler.impl;

import co.com.pragma.backend_challenge.plaza.application.dto.request.order.OrderRequest;
import co.com.pragma.backend_challenge.plaza.application.dto.response.order.OrderCreatedResponse;
import co.com.pragma.backend_challenge.plaza.application.handler.OrderHandler;
import co.com.pragma.backend_challenge.plaza.application.mapper.request.OrderRequestMapper;
import co.com.pragma.backend_challenge.plaza.application.mapper.response.OrderResponseMapper;
import co.com.pragma.backend_challenge.plaza.domain.api.OrderServicePort;
import co.com.pragma.backend_challenge.plaza.domain.model.order.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderHandlerImpl implements OrderHandler {
    private final OrderServicePort orderServicePort;
    private final OrderRequestMapper orderRequestMapper;
    private final OrderResponseMapper orderResponseMapper;

    @Override
    public OrderCreatedResponse createOrder(OrderRequest orderRequest) {
        Order order = orderRequestMapper.toDomain(orderRequest);
        return orderResponseMapper.toCreatedResponse(
                orderServicePort.createOrder(order)
        );
    }
}
