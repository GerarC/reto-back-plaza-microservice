package co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.adapter;

import co.com.pragma.backend_challenge.plaza.domain.model.Employee;
import co.com.pragma.backend_challenge.plaza.domain.spi.persistence.EmployeePersistencePort;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.entity.EmployeeEntity;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.mapper.EmployeeEntityMapper;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeJpaAdapter implements EmployeePersistencePort {
    private final EmployeeRepository employeeRepository;
    private final EmployeeEntityMapper employeeEntityMapper;

    @Override
    public Employee saveEmployee(Employee employee) {
        EmployeeEntity entity = employeeEntityMapper.toEntity(employee);
        return employeeEntityMapper.toDomain(entity);
    }
}
