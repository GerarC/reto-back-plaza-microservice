package co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.adapter;

import co.com.pragma.backend_challenge.plaza.domain.model.order.Order;
import co.com.pragma.backend_challenge.plaza.domain.spi.persistence.OrderPersistencePort;
import co.com.pragma.backend_challenge.plaza.domain.util.filter.OrderFilter;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.entity.OrderEntity;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.mapper.OrderEntityMapper;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.repository.OrderRepository;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.specification.OrderSpecificationBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderJpaAdapter implements OrderPersistencePort {
    private final OrderRepository orderRepository;
    private final OrderEntityMapper orderEntityMapper;

    @Override
    public Order saveOrder(Order order) {
        OrderEntity entity = orderEntityMapper.toEntity(order);
        return orderEntityMapper.toDomain(
                orderRepository.save(entity)
        );
    }

    @Override
    public List<Order> findFilteredOrders(OrderFilter filter) {
        Specification<OrderEntity> specs = OrderSpecificationBuilder.filterBy(filter);
        return orderEntityMapper.toDomains(
                orderRepository.findAll(specs)
        );
    }
}
