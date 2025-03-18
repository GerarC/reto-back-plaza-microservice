package co.com.pragma.backend_challenge.plaza.application.handler.impl;

import co.com.pragma.backend_challenge.plaza.application.dto.request.EmployeeRequest;
import co.com.pragma.backend_challenge.plaza.application.dto.request.RestaurantRequest;
import co.com.pragma.backend_challenge.plaza.application.dto.response.EmployeeResponse;
import co.com.pragma.backend_challenge.plaza.application.dto.response.RestaurantResponse;
import co.com.pragma.backend_challenge.plaza.application.handler.RestaurantHandler;
import co.com.pragma.backend_challenge.plaza.application.mapper.request.EmployeeRequestMapper;
import co.com.pragma.backend_challenge.plaza.application.mapper.request.RestaurantRequestMapper;
import co.com.pragma.backend_challenge.plaza.application.mapper.response.EmployeeResponseMapper;
import co.com.pragma.backend_challenge.plaza.application.mapper.response.RestaurantResponseMapper;
import co.com.pragma.backend_challenge.plaza.domain.api.RestaurantServicePort;
import co.com.pragma.backend_challenge.plaza.domain.model.Employee;
import co.com.pragma.backend_challenge.plaza.domain.model.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantHandlerImpl implements RestaurantHandler {
    private final RestaurantServicePort restaurantServicePort;
    private final RestaurantRequestMapper restaurantRequestMapper;
    private final RestaurantResponseMapper restaurantResponseMapper;
    private final EmployeeRequestMapper employeeRequestMapper;
    private final EmployeeResponseMapper employeeResponseMapper;

    @Override
    public RestaurantResponse createRestaurant(RestaurantRequest restaurantRequest) {
        Restaurant restaurant = restaurantRequestMapper.toDomain(restaurantRequest);
        return restaurantResponseMapper.toResponse(
                restaurantServicePort.createRestaurant(restaurant)
        );
    }

    @Override
    public EmployeeResponse registerEmployee(EmployeeRequest employeeRequest) {
        Employee employee = employeeRequestMapper.toDomain(employeeRequest);
        return employeeResponseMapper.toResponse(
                restaurantServicePort.registerEmployee(employee)
        );
    }
}
