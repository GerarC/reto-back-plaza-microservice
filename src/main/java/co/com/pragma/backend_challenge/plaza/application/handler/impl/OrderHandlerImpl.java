package co.com.pragma.backend_challenge.plaza.application.handler.impl;

import co.com.pragma.backend_challenge.plaza.application.dto.request.OrderPinRequest;
import co.com.pragma.backend_challenge.plaza.application.dto.request.filter.OrderFilterRequest;
import co.com.pragma.backend_challenge.plaza.application.dto.request.order.OrderRequest;
import co.com.pragma.backend_challenge.plaza.application.dto.request.pagination.PaginationRequest;
import co.com.pragma.backend_challenge.plaza.application.dto.response.PageResponse;
import co.com.pragma.backend_challenge.plaza.application.dto.response.order.OrderCreatedResponse;
import co.com.pragma.backend_challenge.plaza.application.dto.response.order.OrderResponse;
import co.com.pragma.backend_challenge.plaza.application.handler.OrderHandler;
import co.com.pragma.backend_challenge.plaza.application.mapper.request.OrderRequestMapper;
import co.com.pragma.backend_challenge.plaza.application.mapper.request.pagination.PaginationRequestMapper;
import co.com.pragma.backend_challenge.plaza.application.mapper.response.OrderResponseMapper;
import co.com.pragma.backend_challenge.plaza.domain.api.OrderServicePort;
import co.com.pragma.backend_challenge.plaza.domain.model.order.Order;
import co.com.pragma.backend_challenge.plaza.domain.util.filter.OrderFilter;
import co.com.pragma.backend_challenge.plaza.domain.util.pagination.PaginationData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderHandlerImpl implements OrderHandler {
    private final OrderServicePort orderServicePort;
    private final OrderRequestMapper orderRequestMapper;
    private final OrderResponseMapper orderResponseMapper;
    private final PaginationRequestMapper paginationRequestMapper;

    @Override
    public OrderCreatedResponse createOrder(OrderRequest orderRequest) {
        Order order = orderRequestMapper.toDomain(orderRequest);
        return orderResponseMapper.toCreatedResponse(
                orderServicePort.createOrder(order)
        );
    }

    @Override
    public PageResponse<OrderResponse> findOrders(OrderFilterRequest filterRequest, PaginationRequest paginationRequest) {
        OrderFilter filter = OrderFilter
                .builder().states(filterRequest.getStates())
                .build();
        PaginationData paginationData = paginationRequestMapper.toDomain(paginationRequest);
        return orderResponseMapper.toResponses(
                orderServicePort.findOrders(filter, paginationData)
        );
    }

    @Override
    public OrderResponse setAssignedEmployee(Long id) {
        return orderResponseMapper.toResponse(
                orderServicePort.setAssignedEmployee(id)
        );
    }

    @Override
    public OrderResponse setOrderAsDone(Long id) {
        return orderResponseMapper.toResponse(
                orderServicePort.setOrderAsDone(id)
        );
    }

    @Override
    public OrderResponse setOrderAsDelivered(Long id, OrderPinRequest pinRequest) {
        return orderResponseMapper.toResponse(
                orderServicePort.setOrderAsDelivered(id, pinRequest.getSecurityPin())
        );
    }

    @Override
    public OrderResponse setOrderAsCanceled(Long id) {
        return orderResponseMapper.toResponse(
                orderServicePort.setOrderAsCanceled(id)
        );
    }
}
