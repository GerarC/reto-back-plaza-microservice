package co.com.pragma.backend_challenge.plaza.domain.usecase;

import co.com.pragma.backend_challenge.plaza.domain.api.OrderServicePort;
import co.com.pragma.backend_challenge.plaza.domain.exception.CustomerAlreadyHasAProcessingOrderException;
import co.com.pragma.backend_challenge.plaza.domain.exception.DishDoesNotBelongToOrderRestaurantException;
import co.com.pragma.backend_challenge.plaza.domain.exception.EntityNotFoundException;
import co.com.pragma.backend_challenge.plaza.domain.model.Dish;
import co.com.pragma.backend_challenge.plaza.domain.model.Restaurant;
import co.com.pragma.backend_challenge.plaza.domain.model.order.Order;
import co.com.pragma.backend_challenge.plaza.domain.model.order.OrderDish;
import co.com.pragma.backend_challenge.plaza.domain.model.security.AuthorizedUser;
import co.com.pragma.backend_challenge.plaza.domain.spi.persistence.DishPersistencePort;
import co.com.pragma.backend_challenge.plaza.domain.spi.persistence.EmployeePersistencePort;
import co.com.pragma.backend_challenge.plaza.domain.spi.persistence.OrderPersistencePort;
import co.com.pragma.backend_challenge.plaza.domain.spi.persistence.RestaurantPersistencePort;
import co.com.pragma.backend_challenge.plaza.domain.spi.security.AuthorizationSecurityPort;
import co.com.pragma.backend_challenge.plaza.domain.util.DomainConstants;
import co.com.pragma.backend_challenge.plaza.domain.util.GenerationUtils;
import co.com.pragma.backend_challenge.plaza.domain.util.TokenHolder;
import co.com.pragma.backend_challenge.plaza.domain.util.enums.OrderState;
import co.com.pragma.backend_challenge.plaza.domain.util.filter.OrderFilter;

import java.util.Objects;

public class OrderUseCase implements OrderServicePort {
    private final OrderPersistencePort orderPersistencePort;
    private final RestaurantPersistencePort restaurantPersistencePort;
    private final AuthorizationSecurityPort authorizationSecurityPort;
    private final DishPersistencePort dishPersistencePort;
    private final EmployeePersistencePort employeePersistencePort;

    public OrderUseCase(OrderPersistencePort orderPersistencePort,
                        RestaurantPersistencePort restaurantPersistencePort,
                        AuthorizationSecurityPort authorizationSecurityPort,
                        DishPersistencePort dishPersistencePort,
                        EmployeePersistencePort employeePersistencePort
    ) {
        this.orderPersistencePort = orderPersistencePort;
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.authorizationSecurityPort = authorizationSecurityPort;
        this.dishPersistencePort = dishPersistencePort;
        this.employeePersistencePort = employeePersistencePort;
    }

    @Override
    public Order createOrder(Order order) {
        AuthorizedUser user = authorizationSecurityPort.authorize(
                TokenHolder.getToken().substring(DomainConstants.TOKEN_PREFIX.length())
        );
        validateCustomerCanAddOrder(order, user);
        order.setState(OrderState.WAITING);
        order.setSecurityPin(GenerationUtils.generateRandomSecurityPin());
        order.setCustomerId(user.getId());
        return orderPersistencePort.saveOrder(order);
    }

    private void validateCustomerCanAddOrder(Order order, AuthorizedUser user) {
        // Current user has not another processing order
        OrderFilter filter = OrderFilter.builder()
                .customerId(user.getId())
                .states(DomainConstants.PROCESS_STATES)
                .build();
        if(!orderPersistencePort.findFilteredOrders(filter).isEmpty())
            throw new CustomerAlreadyHasAProcessingOrderException();

        // Restaurant Exists
        Restaurant restaurant = restaurantPersistencePort.findById(order.getRestaurant().getId());
        if (restaurant == null)
            throw new EntityNotFoundException(Restaurant.class.getSimpleName(), order.getRestaurant().getId());

        // Dishes exist
        order.getDishes().forEach(dish -> validateOrderDish(dish, restaurant));
    }

    /**
     * Receives an OrderDish and a restaurant, verify that dish exists and belongs to the given restaurant.
     * Throws EntityNotFoundException and DishDoesNotBelongToRestaurantException.
     *
     * @param orderDish an OrderDish with dishId and quantity.
     * @param restaurant a pre-found restaurant.
     */
    private void validateOrderDish(OrderDish orderDish, Restaurant restaurant){
        Dish dish =  dishPersistencePort.findById(orderDish.getDish().getId());
        if(dish == null) throw new EntityNotFoundException(
                Dish.class.getSimpleName(),
                orderDish.getDish().getId().toString()
    );
        if(!Objects.equals(dish.getRestaurant().getId(), restaurant.getId()))
            throw new DishDoesNotBelongToOrderRestaurantException(dish.getName(), restaurant.getName());
    }
}
