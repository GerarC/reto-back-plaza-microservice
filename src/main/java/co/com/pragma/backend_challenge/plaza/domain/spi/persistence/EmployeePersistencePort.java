package co.com.pragma.backend_challenge.plaza.domain.spi.persistence;

import co.com.pragma.backend_challenge.plaza.domain.model.Employee;

public interface EmployeePersistencePort {
    Employee saveEmployee(Employee employee);
}
