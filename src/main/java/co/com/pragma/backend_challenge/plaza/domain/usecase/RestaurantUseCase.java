package co.com.pragma.backend_challenge.plaza.domain.usecase;

import co.com.pragma.backend_challenge.plaza.domain.api.RestaurantServicePort;
import co.com.pragma.backend_challenge.plaza.domain.exception.EntityAlreadyExistsException;
import co.com.pragma.backend_challenge.plaza.domain.exception.EntityNotFoundException;
import co.com.pragma.backend_challenge.plaza.domain.exception.RestaurantDoesNotBelongToUserException;
import co.com.pragma.backend_challenge.plaza.domain.exception.UserRoleMustBeOwnerException;
import co.com.pragma.backend_challenge.plaza.domain.model.Dish;
import co.com.pragma.backend_challenge.plaza.domain.model.Employee;
import co.com.pragma.backend_challenge.plaza.domain.model.Restaurant;
import co.com.pragma.backend_challenge.plaza.domain.model.security.AuthorizedUser;
import co.com.pragma.backend_challenge.plaza.domain.spi.persistence.DishPersistencePort;
import co.com.pragma.backend_challenge.plaza.domain.spi.persistence.EmployeePersistencePort;
import co.com.pragma.backend_challenge.plaza.domain.spi.persistence.RestaurantPersistencePort;
import co.com.pragma.backend_challenge.plaza.domain.spi.persistence.UserPersistencePort;
import co.com.pragma.backend_challenge.plaza.domain.spi.security.AuthorizationSecurityPort;
import co.com.pragma.backend_challenge.plaza.domain.util.DomainConstants;
import co.com.pragma.backend_challenge.plaza.domain.util.TokenHolder;
import co.com.pragma.backend_challenge.plaza.domain.util.enums.DishState;
import co.com.pragma.backend_challenge.plaza.domain.util.filter.DishFilter;
import co.com.pragma.backend_challenge.plaza.domain.util.pagination.DomainPage;
import co.com.pragma.backend_challenge.plaza.domain.util.pagination.PaginationData;

import java.util.Objects;

public class RestaurantUseCase implements RestaurantServicePort {
    private final RestaurantPersistencePort restaurantPersistencePort;
    private final UserPersistencePort userPersistencePort;
    private final EmployeePersistencePort employeePersistencePort;
    private final AuthorizationSecurityPort authorizationSecurityPort;
    private final DishPersistencePort dishPersistencePort;

    public RestaurantUseCase(RestaurantPersistencePort restaurantPersistencePort,
                             UserPersistencePort userPersistencePort,
                             EmployeePersistencePort employeePersistencePort,
                             AuthorizationSecurityPort authorizationSecurityPort,
                             DishPersistencePort dishPersistencePort) {
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.userPersistencePort = userPersistencePort;
        this.employeePersistencePort = employeePersistencePort;
        this.authorizationSecurityPort = authorizationSecurityPort;
        this.dishPersistencePort = dishPersistencePort;
    }

    @Override
    public Restaurant createRestaurant(Restaurant restaurant) {
        validateRestaurantData(restaurant);
        return restaurantPersistencePort.saveRestaurant(restaurant);
    }

    @Override
    public Employee registerEmployee(Employee employee) {
        Restaurant restaurant = restaurantPersistencePort.findById(employee.getRestaurant().getId());
        if (restaurant == null)
            throw new EntityNotFoundException(Restaurant.class.getSimpleName(), employee.getRestaurant().getId());
        AuthorizedUser user = authorizationSecurityPort.authorize(TokenHolder.getToken().substring(DomainConstants.TOKEN_PREFIX.length()));
        employee.setRestaurant(restaurant);
        if(!Objects.equals(user.getId(), restaurant.getOwnerId()))
            throw new RestaurantDoesNotBelongToUserException();
        return employeePersistencePort.saveEmployee(employee);
    }

    @Override
    public DomainPage<Restaurant> findPage(PaginationData paginationData) {
        paginationData.setColumn(DomainConstants.NAME_FIELD);
        return restaurantPersistencePort.findAll(paginationData);
    }

    @Override
    public DomainPage<Dish> findDishesOfRestaurant(String id, String category, PaginationData paginationData) {
        if(restaurantPersistencePort.findById(id) == null)
            throw new EntityNotFoundException(Restaurant.class.getSimpleName(), id);
        DishFilter filter = DishFilter.builder()
                .restaurantId(id)
                .category(category)
                .state(DishState.ACTIVE)
                .build();
        return dishPersistencePort.findAll(filter, paginationData);
    }

    private void validateRestaurantData(Restaurant restaurant){
        if(!userPersistencePort.isOwner(restaurant.getOwnerId()))
            throw new UserRoleMustBeOwnerException();
        if(restaurantPersistencePort.findByNit(restaurant.getNit())!=null)
            throw new EntityAlreadyExistsException(Restaurant.class.getSimpleName(), restaurant.getNit());
    }
}
