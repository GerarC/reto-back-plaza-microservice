package co.com.pragma.backend_challenge.plaza.domain.spi.persistence;

import co.com.pragma.backend_challenge.plaza.domain.model.Employee;
import co.com.pragma.backend_challenge.plaza.domain.util.filter.EmployeeFilter;

import java.util.List;

public interface EmployeePersistencePort {
    Employee saveEmployee(Employee employee);
    List<Employee> findAll(EmployeeFilter filter);
    Employee findById(String id);
}
