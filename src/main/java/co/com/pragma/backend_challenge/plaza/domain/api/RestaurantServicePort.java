package co.com.pragma.backend_challenge.plaza.domain.api;

import co.com.pragma.backend_challenge.plaza.domain.model.Employee;
import co.com.pragma.backend_challenge.plaza.domain.model.Restaurant;

public interface RestaurantServicePort {
    Restaurant createRestaurant(Restaurant restaurant);
    Employee registerEmployee(Employee employee);
}
