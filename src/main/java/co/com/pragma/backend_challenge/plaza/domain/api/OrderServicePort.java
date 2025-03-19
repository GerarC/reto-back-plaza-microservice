package co.com.pragma.backend_challenge.plaza.domain.api;

import co.com.pragma.backend_challenge.plaza.application.dto.response.PageResponse;
import co.com.pragma.backend_challenge.plaza.application.dto.response.order.OrderResponse;
import co.com.pragma.backend_challenge.plaza.domain.model.order.Order;
import co.com.pragma.backend_challenge.plaza.domain.util.filter.OrderFilter;
import co.com.pragma.backend_challenge.plaza.domain.util.pagination.DomainPage;
import co.com.pragma.backend_challenge.plaza.domain.util.pagination.PaginationData;

public interface OrderServicePort {
    Order createOrder(Order order);
    DomainPage<Order> findOrders(OrderFilter filter, PaginationData paginationData);
}
