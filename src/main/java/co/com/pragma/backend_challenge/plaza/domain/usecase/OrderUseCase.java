package co.com.pragma.backend_challenge.plaza.domain.usecase;

import co.com.pragma.backend_challenge.plaza.domain.api.OrderServicePort;
import co.com.pragma.backend_challenge.plaza.domain.exception.*;
import co.com.pragma.backend_challenge.plaza.domain.model.User;
import co.com.pragma.backend_challenge.plaza.domain.model.Dish;
import co.com.pragma.backend_challenge.plaza.domain.model.Employee;
import co.com.pragma.backend_challenge.plaza.domain.model.Restaurant;
import co.com.pragma.backend_challenge.plaza.domain.model.messaging.Notification;
import co.com.pragma.backend_challenge.plaza.domain.model.order.Order;
import co.com.pragma.backend_challenge.plaza.domain.model.order.OrderDish;
import co.com.pragma.backend_challenge.plaza.domain.model.security.AuthorizedUser;
import co.com.pragma.backend_challenge.plaza.domain.spi.messaging.NotificationSenderPort;
import co.com.pragma.backend_challenge.plaza.domain.spi.persistence.*;
import co.com.pragma.backend_challenge.plaza.domain.spi.security.AuthorizationSecurityPort;
import co.com.pragma.backend_challenge.plaza.domain.util.DomainConstants;
import co.com.pragma.backend_challenge.plaza.domain.util.GenerationUtils;
import co.com.pragma.backend_challenge.plaza.domain.util.TokenHolder;
import co.com.pragma.backend_challenge.plaza.domain.util.enums.OrderState;
import co.com.pragma.backend_challenge.plaza.domain.util.enums.RoleName;
import co.com.pragma.backend_challenge.plaza.domain.util.filter.OrderFilter;
import co.com.pragma.backend_challenge.plaza.domain.util.pagination.DomainPage;
import co.com.pragma.backend_challenge.plaza.domain.util.pagination.PaginationData;

import java.util.Objects;

public class OrderUseCase implements OrderServicePort {
    private final OrderPersistencePort orderPersistencePort;
    private final RestaurantPersistencePort restaurantPersistencePort;
    private final AuthorizationSecurityPort authorizationSecurityPort;
    private final DishPersistencePort dishPersistencePort;
    private final EmployeePersistencePort employeePersistencePort;
    private final UserPersistencePort userPersistencePort;
    private final NotificationSenderPort notificationSenderPort;

    public OrderUseCase(OrderPersistencePort orderPersistencePort,
                        RestaurantPersistencePort restaurantPersistencePort,
                        AuthorizationSecurityPort authorizationSecurityPort,
                        DishPersistencePort dishPersistencePort,
                        EmployeePersistencePort employeePersistencePort,
                        UserPersistencePort userPersistencePort,
                        NotificationSenderPort notificationSenderPort
    ) {
        this.orderPersistencePort = orderPersistencePort;
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.authorizationSecurityPort = authorizationSecurityPort;
        this.dishPersistencePort = dishPersistencePort;
        this.employeePersistencePort = employeePersistencePort;
        this.userPersistencePort = userPersistencePort;
        this.notificationSenderPort = notificationSenderPort;
    }

    @Override
    public Order createOrder(Order order) {
        AuthorizedUser user = getCurrentUser();
        validateCustomerCanAddOrder(order, user);
        order.setState(OrderState.WAITING);
        order.setSecurityPin(GenerationUtils.generateRandomSecurityPin());
        order.setCustomerId(user.getId());
        return orderPersistencePort.saveOrder(order);
    }

    @Override
    public DomainPage<Order> findOrders(OrderFilter filter, PaginationData paginationData) {
        AuthorizedUser user = getCurrentUser();
        if (user.getRole() != RoleName.EMPLOYEE)
            throw new NotAuthorizedException();
        Employee employee = employeePersistencePort.findById(user.getId());
        filter.setRestaurantId(employee.getRestaurant().getId());
        return orderPersistencePort.findOrders(filter, paginationData);
    }

    @Override
    public Order setAssignedEmployee(Long id) {
        Order order = getOrder(id);
        if (order.getAssignedEmployee() != null) throw new OrderIsAssignedToAnotherEmployeeException();

        AuthorizedUser user = getCurrentUser();
        if (user.getRole() != RoleName.EMPLOYEE) throw new NotAuthorizedException();

        Employee employee = employeePersistencePort.findById(user.getId());
        if (!Objects.equals(employee.getRestaurant().getId(), order.getRestaurant().getId()))
            throw new NotAuthorizedException();
        order.setAssignedEmployee(employee);
        order.setState(OrderState.PREPARING);
        return orderPersistencePort.updateOrder(order);
    }

    @Override
    public Order setOrderAsDone(Long id) {
        AuthorizedUser user = getCurrentUser();
        if (user.getRole() != RoleName.EMPLOYEE) throw new NotAuthorizedException();
        Order order = getOrder(id);

        if (!Objects.equals(order.getAssignedEmployee().getId(), user.getId()))
            throw new OrderIsAssignedToAnotherEmployeeException();
        if (order.getState() != OrderState.PREPARING) throw new OrderIsNotInPreparationStateException();

        try {
            notificationSenderPort.sendNotification(
                    buildNotification(order.getCustomerId(), order.getSecurityPin())
            );
        } catch (Exception e) {
            throw new NotificationWasNotSentException();
        }

        order.setState(OrderState.DONE);
        return orderPersistencePort.updateOrder(order);
    }

    @Override
    public Order setOrderAsDelivered(Long id, String securityPin) {
        AuthorizedUser user = getCurrentUser();
        if (user.getRole() != RoleName.EMPLOYEE) throw new NotAuthorizedException();
        Order order = getOrder(id);

        if (!Objects.equals(order.getAssignedEmployee().getId(), user.getId()))
            throw new OrderIsAssignedToAnotherEmployeeException();
        if (order.getState() != OrderState.DONE) throw new OrderIsNotDoneException();
        if (!Objects.equals(securityPin, order.getSecurityPin())) throw new SecurityPinDoesNotMatchException();

        order.setState(OrderState.DELIVERED);
        return orderPersistencePort.updateOrder(order);
    }

    private void validateCustomerCanAddOrder(Order order, AuthorizedUser user) {
        // Current user has not another processing order
        OrderFilter filter = OrderFilter.builder()
                .customerId(user.getId())
                .states(DomainConstants.PROCESS_STATES)
                .build();
        if (!orderPersistencePort.findFilteredOrders(filter).isEmpty())
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
     * @param orderDish  an OrderDish with dishId and quantity.
     * @param restaurant a pre-found restaurant.
     */
    private void validateOrderDish(OrderDish orderDish, Restaurant restaurant) {
        Dish dish = dishPersistencePort.findById(orderDish.getDish().getId());
        if (dish == null) throw new EntityNotFoundException(
                Dish.class.getSimpleName(),
                orderDish.getDish().getId().toString()
        );
        if (!Objects.equals(dish.getRestaurant().getId(), restaurant.getId()))
            throw new DishDoesNotBelongToOrderRestaurantException(dish.getName(), restaurant.getName());
    }

    private AuthorizedUser getCurrentUser() {
        return authorizationSecurityPort.authorize(
                TokenHolder.getToken().substring(DomainConstants.TOKEN_PREFIX.length())
        );
    }

    private Notification buildNotification(String customerId, String code) {
        User user = userPersistencePort.getUser(customerId);
        return Notification.builder()
                .receiver(user.getPhone())
                .message(String.format(
                        DomainConstants.NOTIFICATION_MESSAGE_TEMPLATE,
                        user.getName(), user.getLastname(), code))
                .build();
    }

    private Order getOrder(Long id) {
        Order order = orderPersistencePort.findById(id);
        if (order == null) throw new EntityNotFoundException(Order.class.getSimpleName(), id.toString());
        return order;
    }
}
