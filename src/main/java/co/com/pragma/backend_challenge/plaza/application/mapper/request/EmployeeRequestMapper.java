package co.com.pragma.backend_challenge.plaza.application.mapper.request;

import co.com.pragma.backend_challenge.plaza.application.dto.request.EmployeeRequest;
import co.com.pragma.backend_challenge.plaza.domain.model.Employee;
import co.com.pragma.backend_challenge.plaza.domain.model.Restaurant;
import co.com.pragma.backend_challenge.plaza.domain.util.annotation.Generated;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Generated
@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeRequestMapper {
    default Restaurant toRestaurant(String restaurantId){
        return Restaurant.builder()
                .id(restaurantId)
                .build();
    }
    @Mapping(target = "restaurant", source = "restaurantId")
    Employee toDomain(EmployeeRequest request);
    List<Employee> toDomains(List<EmployeeRequest> requests);
}
