package co.com.pragma.backend_challenge.plaza.application.mapper.response;

import co.com.pragma.backend_challenge.plaza.application.dto.response.EmployeeResponse;
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
public interface EmployeeResponseMapper {
    default String toRestaurantId(Restaurant restaurant){
        return restaurant.getId();
    }

    @Mapping(target = "restaurantId", source = "restaurant")
    EmployeeResponse toResponse(Employee restaurant);
    List<EmployeeResponse> toResponses(List<Employee> restaurants);
}
