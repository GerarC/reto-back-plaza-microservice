package co.com.pragma.backend_challenge.plaza.domain.api;

import co.com.pragma.backend_challenge.plaza.domain.model.Employee;
import co.com.pragma.backend_challenge.plaza.domain.model.Restaurant;
import co.com.pragma.backend_challenge.plaza.domain.util.pagination.DomainPage;
import co.com.pragma.backend_challenge.plaza.domain.util.pagination.PaginationData;

import java.util.List;

public interface RestaurantServicePort {
    Restaurant createRestaurant(Restaurant restaurant);
    Employee registerEmployee(Employee employee);
    DomainPage<Restaurant> findPage(PaginationData paginationData);
}
