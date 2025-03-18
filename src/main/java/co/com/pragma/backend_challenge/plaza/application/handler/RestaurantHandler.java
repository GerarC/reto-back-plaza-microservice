package co.com.pragma.backend_challenge.plaza.application.handler;

import co.com.pragma.backend_challenge.plaza.application.dto.request.EmployeeRequest;
import co.com.pragma.backend_challenge.plaza.application.dto.request.RestaurantRequest;
import co.com.pragma.backend_challenge.plaza.application.dto.request.pagination.PaginationRequest;
import co.com.pragma.backend_challenge.plaza.application.dto.response.EmployeeResponse;
import co.com.pragma.backend_challenge.plaza.application.dto.response.PageResponse;
import co.com.pragma.backend_challenge.plaza.application.dto.response.RestaurantResponse;

public interface RestaurantHandler {
    RestaurantResponse createRestaurant(RestaurantRequest restaurantRequest);
    EmployeeResponse registerEmployee(EmployeeRequest employeeRequest);
    PageResponse<RestaurantResponse> findPage(PaginationRequest pagination);
}
