package co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.mapper;

import co.com.pragma.backend_challenge.plaza.domain.model.Employee;
import co.com.pragma.backend_challenge.plaza.domain.util.annotation.Generated;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.jpa.entity.EmployeeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Generated
@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeEntityMapper {
    EmployeeEntity toEntity(Employee employee);
    Employee toDomain(EmployeeEntity entity);
    List<Employee> toDomains(List<EmployeeEntity> entities);
}
