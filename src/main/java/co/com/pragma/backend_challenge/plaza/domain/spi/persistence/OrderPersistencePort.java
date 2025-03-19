package co.com.pragma.backend_challenge.plaza.domain.spi.persistence;

import co.com.pragma.backend_challenge.plaza.domain.model.order.Order;
import co.com.pragma.backend_challenge.plaza.domain.util.filter.OrderFilter;

import java.util.List;

public interface OrderPersistencePort {
    Order saveOrder(Order order);
    List<Order> findFilteredOrders(OrderFilter filter);
}
