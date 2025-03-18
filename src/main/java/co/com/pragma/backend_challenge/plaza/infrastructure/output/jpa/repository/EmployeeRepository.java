package co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.repository;

import co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, String> {
}
