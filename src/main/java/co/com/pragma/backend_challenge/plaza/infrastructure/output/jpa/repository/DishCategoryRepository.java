package co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.repository;

import co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.entity.DishCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DishCategoryRepository extends JpaRepository<DishCategoryEntity, Long> {
    Optional<DishCategoryEntity> findByDescription(String description);
}
