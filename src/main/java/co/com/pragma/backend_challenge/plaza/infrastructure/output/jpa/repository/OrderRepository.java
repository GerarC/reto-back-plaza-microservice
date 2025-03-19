package co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.repository;

import co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findAll(Specification<OrderEntity> specs);
    Page<OrderEntity> findAll(Specification<OrderEntity> specs, Pageable pageable);
}
