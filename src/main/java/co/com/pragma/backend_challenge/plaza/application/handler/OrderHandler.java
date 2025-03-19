package co.com.pragma.backend_challenge.plaza.application.handler;

import co.com.pragma.backend_challenge.plaza.application.dto.request.filter.OrderFilterRequest;
import co.com.pragma.backend_challenge.plaza.application.dto.request.order.OrderRequest;
import co.com.pragma.backend_challenge.plaza.application.dto.request.pagination.PaginationRequest;
import co.com.pragma.backend_challenge.plaza.application.dto.response.PageResponse;
import co.com.pragma.backend_challenge.plaza.application.dto.response.order.OrderCreatedResponse;
import co.com.pragma.backend_challenge.plaza.application.dto.response.order.OrderResponse;

public interface OrderHandler {
    OrderCreatedResponse createOrder(OrderRequest orderRequest);
    PageResponse<OrderResponse> findOrders(OrderFilterRequest filter, PaginationRequest paginationRequest);
}
