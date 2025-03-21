package co.com.pragma.backend_challenge.plaza.application.handler;

import co.com.pragma.backend_challenge.plaza.application.dto.request.EmployeeRequest;
import co.com.pragma.backend_challenge.plaza.application.dto.request.RestaurantRequest;
import co.com.pragma.backend_challenge.plaza.application.dto.request.filter.DishFilterRequest;
import co.com.pragma.backend_challenge.plaza.application.dto.request.pagination.PaginationRequest;
import co.com.pragma.backend_challenge.plaza.application.dto.response.*;

public interface RestaurantHandler {
    RestaurantResponse createRestaurant(RestaurantRequest restaurantRequest);
    EmployeeResponse registerEmployee(EmployeeRequest employeeRequest);
    PageResponse<RestaurantResponse> findPage(PaginationRequest pagination);
    PageResponse<DishResponse> findDishesOfRestaurant(String id, PaginationRequest pagination, DishFilterRequest filterRequest);
    OwnerRestaurantResponse findCurrentUserRestaurant();
}
