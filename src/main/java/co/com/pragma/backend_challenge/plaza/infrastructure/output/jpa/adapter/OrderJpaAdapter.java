package co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.adapter;

import co.com.pragma.backend_challenge.plaza.domain.model.order.Order;
import co.com.pragma.backend_challenge.plaza.domain.spi.persistence.OrderPersistencePort;
import co.com.pragma.backend_challenge.plaza.domain.util.filter.OrderFilter;
import co.com.pragma.backend_challenge.plaza.domain.util.pagination.DomainPage;
import co.com.pragma.backend_challenge.plaza.domain.util.pagination.PaginationData;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.entity.OrderEntity;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.mapper.OrderEntityMapper;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.mapper.PaginationJpaMapper;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.repository.OrderRepository;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.specification.OrderSpecificationBuilder;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.util.PaginationJpa;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderJpaAdapter implements OrderPersistencePort {
    private final OrderRepository orderRepository;
    private final OrderEntityMapper orderEntityMapper;
    private final PaginationJpaMapper paginationJpaMapper;
    private final EntityManager entityManager;

    @Transactional
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

    @Override
    public DomainPage<Order> findOrders(OrderFilter filter, PaginationData paginationData) {
        Specification<OrderEntity> specs = OrderSpecificationBuilder.filterBy(filter);
        PaginationJpa paginationJpa = paginationJpaMapper.toJpa(paginationData);
        return orderEntityMapper.toDomains(
                orderRepository.findAll(specs, paginationJpa.createPageable())
        );
    }

    @Override
    public Order findById(Long id) {
        return orderEntityMapper.toDomain(
                orderRepository.findById(id).orElse(null)
        );
    }

    @Override
    public Order updateOrder(Order order) {
        OrderEntity entity = orderEntityMapper.toEntity(order);
        entity.getDishes().forEach(entityManager::detach);
        return orderEntityMapper.toDomain(
                orderRepository.save(entity)
        );
    }
}
