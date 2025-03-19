package co.com.pragma.backend_challenge.plaza.domain.api;

import co.com.pragma.backend_challenge.plaza.domain.model.order.Order;

public interface OrderServicePort {
    Order createOrder(Order order);
}
