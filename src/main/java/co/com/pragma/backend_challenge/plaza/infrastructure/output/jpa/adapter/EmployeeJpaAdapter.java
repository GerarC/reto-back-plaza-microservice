package co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.adapter;

import co.com.pragma.backend_challenge.plaza.domain.model.Employee;
import co.com.pragma.backend_challenge.plaza.domain.spi.persistence.EmployeePersistencePort;
import co.com.pragma.backend_challenge.plaza.domain.util.filter.EmployeeFilter;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.entity.EmployeeEntity;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.mapper.EmployeeEntityMapper;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.repository.EmployeeRepository;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.specification.EmployeeSpecificationBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EmployeeJpaAdapter implements EmployeePersistencePort {
    private final EmployeeRepository employeeRepository;
    private final EmployeeEntityMapper employeeEntityMapper;

    @Override
    public Employee saveEmployee(Employee employee) {
        EmployeeEntity entity = employeeEntityMapper.toEntity(employee);
        return employeeEntityMapper.toDomain(
                employeeRepository.save(entity)
        );
    }

    @Override
    public List<Employee> findAll(EmployeeFilter filter) {
        Specification<EmployeeEntity> specs = EmployeeSpecificationBuilder.filterBy(filter);
        return employeeEntityMapper.toDomains(
                employeeRepository.findAll(specs)
        );
    }

    @Override
    public Employee findById(String id) {
        return employeeEntityMapper.toDomain(
                employeeRepository.findById(id).orElse(null)
        );
    }
}
