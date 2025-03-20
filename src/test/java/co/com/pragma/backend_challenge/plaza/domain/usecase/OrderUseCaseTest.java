package co.com.pragma.backend_challenge.plaza.domain.usecase;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import co.com.pragma.backend_challenge.plaza.domain.exception.*;
import co.com.pragma.backend_challenge.plaza.domain.model.Dish;
import co.com.pragma.backend_challenge.plaza.domain.model.Employee;
import co.com.pragma.backend_challenge.plaza.domain.model.Restaurant;
import co.com.pragma.backend_challenge.plaza.domain.model.User;
import co.com.pragma.backend_challenge.plaza.domain.model.messaging.Notification;
import co.com.pragma.backend_challenge.plaza.domain.model.order.Order;
import co.com.pragma.backend_challenge.plaza.domain.model.order.OrderDish;
import co.com.pragma.backend_challenge.plaza.domain.model.security.AuthorizedUser;
import co.com.pragma.backend_challenge.plaza.domain.spi.messaging.NotificationSenderPort;
import co.com.pragma.backend_challenge.plaza.domain.spi.persistence.*;
import co.com.pragma.backend_challenge.plaza.domain.spi.security.AuthorizationSecurityPort;
import co.com.pragma.backend_challenge.plaza.domain.util.TokenHolder;
import co.com.pragma.backend_challenge.plaza.domain.util.enums.OrderState;
import co.com.pragma.backend_challenge.plaza.domain.util.enums.RoleName;
import co.com.pragma.backend_challenge.plaza.domain.util.filter.OrderFilter;
import co.com.pragma.backend_challenge.plaza.domain.util.pagination.DomainPage;
import co.com.pragma.backend_challenge.plaza.domain.util.pagination.PaginationData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Collections;
import java.util.List;

class OrderUseCaseTest {

    private static final String USER_ID = "user-id";
    private static final RoleName USER_ROLE = RoleName.CUSTOMER;
    private static final String USER_TOKEN = "user-authorization-token";
    private static final Long ORDER_ID = 1L;
    private static final String RESTAURANT_ID = "restaurant-id";
    private static final String RESTAURANT_NAME = "Mock Restaurant";
    private static final Long DISH_ID = 101L;
    private static final String DISH_NAME = "Mock Dish";

    private static final String EMPLOYEE_ID = "EMPLOYEE_123";
    private static final String CUSTOMER_ID = "CUSTOMER_456";
    private static final String SECURITY_PIN = "1234";
    private static final String CUSTOMER_PHONE = "9876543210";
    private static final String CUSTOMER_NAME = "John";
    private static final String CUSTOMER_LASTNAME = "Doe";

    private static final AuthorizedUser mockUser = AuthorizedUser.builder()
            .role(USER_ROLE)
            .token(USER_TOKEN)
            .id(USER_ID)
            .build();

    @Mock
    private OrderPersistencePort orderPersistencePort;
    @Mock
    private RestaurantPersistencePort restaurantPersistencePort;
    @Mock
    private DishPersistencePort dishPersistencePort;
    @Mock
    private AuthorizationSecurityPort authorizationSecurityPort;
    @Mock
    private EmployeePersistencePort employeePersistencePort;
    @Mock
    private NotificationSenderPort notificationSenderPort;
    @Mock
    private UserPersistencePort userPersistencePort;

    @InjectMocks
    private OrderUseCase orderUseCase;

    private static final Restaurant mockRestaurant = Restaurant.builder()
            .id(RESTAURANT_ID)
            .name(RESTAURANT_NAME)
            .build();

    private static final Dish mockDish = Dish.builder()
            .id(DISH_ID)
            .name(DISH_NAME)
            .restaurant(mockRestaurant)
            .build();

    private static final Order mockOrder = Order.builder()
            .id(ORDER_ID)
            .customerId(USER_ID)
            .restaurant(mockRestaurant)
            .dishes(List.of(
                    OrderDish.builder()
                            .id(DISH_ID)
                            .dish(mockDish)
                            .quantity(1)
                            .build()))
            .state(OrderState.WAITING)
            .build();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        TokenHolder.setToken(USER_TOKEN);
    }

    @Test
    void createOrder_Success() {
        when(authorizationSecurityPort.authorize(any())).thenReturn(mockUser);
        when(orderPersistencePort.findFilteredOrders(any())).thenReturn(Collections.emptyList());
        when(restaurantPersistencePort.findById(any())).thenReturn(mockRestaurant);
        when(dishPersistencePort.findById(any())).thenReturn(mockDish);
        when(orderPersistencePort.saveOrder(any())).thenReturn(mockOrder);

        Order createdOrder = orderUseCase.createOrder(mockOrder);

        verify(orderPersistencePort).saveOrder(any());
        assertNotNull(createdOrder);
    }

    @Test
    void createOrder_CustomerHasProcessingOrder() {
        when(authorizationSecurityPort.authorize(any())).thenReturn(mockUser);
        when(orderPersistencePort.findFilteredOrders(any())).thenReturn(List.of(mockOrder));

        assertThrows(CustomerAlreadyHasAProcessingOrderException.class, () -> orderUseCase.createOrder(mockOrder));
    }

    @Test
    void createOrder_RestaurantNotFound() {
        when(authorizationSecurityPort.authorize(any())).thenReturn(mockUser);
        when(orderPersistencePort.findFilteredOrders(any())).thenReturn(Collections.emptyList());
        when(restaurantPersistencePort.findById(any())).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> orderUseCase.createOrder(mockOrder));
    }

    @Test
    void createOrder_DishNotFound() {
        when(authorizationSecurityPort.authorize(any())).thenReturn(mockUser);
        when(orderPersistencePort.findFilteredOrders(any())).thenReturn(Collections.emptyList());
        when(restaurantPersistencePort.findById(any())).thenReturn(mockRestaurant);
        when(dishPersistencePort.findById(any())).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> orderUseCase.createOrder(mockOrder));
    }

    @Test
    void findOrders_Success() {
        AuthorizedUser mockEmployee = AuthorizedUser.builder()
                .role(RoleName.EMPLOYEE)
                .id(USER_ID)
                .token(USER_TOKEN)
                .build();

        Employee mockEmployeeEntity = Employee.builder()
                .id(USER_ID)
                .restaurant(mockRestaurant)
                .build();

        OrderFilter filter = OrderFilter.builder().build();
        PaginationData paginationData =  PaginationData.builder().page(0).pageSize(10).build();
        DomainPage<Order> mockOrdersPage = DomainPage.<Order>builder()
                .page(1)
                .content(List.of(mockOrder))
                .totalCount(1L)
                .build();

        when(authorizationSecurityPort.authorize(any())).thenReturn(mockEmployee);
        when(employeePersistencePort.findById(USER_ID)).thenReturn(mockEmployeeEntity);
        when(orderPersistencePort.findOrders(any(), any())).thenReturn(mockOrdersPage);

        DomainPage<Order> result = orderUseCase.findOrders(filter, paginationData);

        assertNotNull(result);
        assertEquals(1L, result.getTotalCount());
        verify(orderPersistencePort).findOrders(any(), any());
    }

    @Test
    void findOrders_NotAuthorized() {
        AuthorizedUser mockCustomer = AuthorizedUser.builder()
                .role(RoleName.CUSTOMER)
                .id(USER_ID)
                .token(USER_TOKEN)
                .build();

        when(authorizationSecurityPort.authorize(any())).thenReturn(mockCustomer);
        OrderFilter filter = OrderFilter.builder().build();
        PaginationData paginationData =  PaginationData.builder().page(0).pageSize(10).build();

        assertThrows(NotAuthorizedException.class, () ->
                orderUseCase.findOrders(filter, paginationData));
    }

    @Test
    void shouldSetOrderAsDoneSuccessfully() {
        // Given
        AuthorizedUser employee = AuthorizedUser.builder()
                .id(EMPLOYEE_ID)
                .role(RoleName.EMPLOYEE)
                .build();

        Order order = Order.builder()
                .id(1L)
                .assignedEmployee(Employee.builder().id(EMPLOYEE_ID).build())
                .state(OrderState.PREPARING)
                .customerId(CUSTOMER_ID)
                .securityPin(SECURITY_PIN)
                .build();

        User customer = User.builder()
                .name(CUSTOMER_NAME)
                .lastname(CUSTOMER_LASTNAME)
                .phone(CUSTOMER_PHONE)
                .build();

        when(authorizationSecurityPort.authorize(anyString())).thenReturn(employee);
        when(orderPersistencePort.findById(1L)).thenReturn(order);
        when(userPersistencePort.getUser(CUSTOMER_ID)).thenReturn(customer);
        when(orderPersistencePort.updateOrder(any(Order.class))).thenReturn(order);

        // When
        Order result = orderUseCase.setOrderAsDone(1L);

        // Then
        assertEquals(OrderState.DONE, result.getState());
        verify(notificationSenderPort, times(1)).sendNotification(any(Notification.class));
        verify(orderPersistencePort, times(1)).updateOrder(any(Order.class));
    }

    @Test
    void shouldThrowNotAuthorizedException_WhenUserIsNotEmployee() {
        // Given
        AuthorizedUser unauthorizedUser = AuthorizedUser.builder()
                .id("USER_123")
                .role(RoleName.CUSTOMER)
                .build();

        when(authorizationSecurityPort.authorize(anyString())).thenReturn(unauthorizedUser);

        // When & Then
        assertThrows(NotAuthorizedException.class, () -> orderUseCase.setOrderAsDone(1L));
        verifyNoInteractions(orderPersistencePort);
    }

    @Test
    void shouldThrowEntityNotFoundException_WhenOrderNotFound() {
        // Given
        AuthorizedUser employee = AuthorizedUser.builder()
                .id(EMPLOYEE_ID)
                .role(RoleName.EMPLOYEE)
                .build();

        when(authorizationSecurityPort.authorize(anyString())).thenReturn(employee);
        when(orderPersistencePort.findById(1L)).thenReturn(null);

        // When & Then
        assertThrows(EntityNotFoundException.class, () -> orderUseCase.setOrderAsDone(1L));
    }

    @Test
    void shouldThrowOrderIsAssignedToAnotherEmployeeException_WhenEmployeeMismatch() {
        // Given
        AuthorizedUser employee = AuthorizedUser.builder()
                .id("DIFFERENT_EMPLOYEE")
                .role(RoleName.EMPLOYEE)
                .build();

        Order order = Order.builder()
                .id(1L)
                .assignedEmployee(Employee.builder().id(EMPLOYEE_ID).build())
                .state(OrderState.PREPARING)
                .build();

        when(authorizationSecurityPort.authorize(anyString())).thenReturn(employee);
        when(orderPersistencePort.findById(1L)).thenReturn(order);

        // When & Then
        assertThrows(OrderIsAssignedToAnotherEmployeeException.class, () -> orderUseCase.setOrderAsDone(1L));
    }

    @Test
    void shouldThrowOrderIsNotInPreparationStateException_WhenOrderNotPreparing() {
        // Given
        AuthorizedUser employee = AuthorizedUser.builder()
                .id(EMPLOYEE_ID)
                .role(RoleName.EMPLOYEE)
                .build();

        Order order = Order.builder()
                .id(1L)
                .assignedEmployee(Employee.builder().id(EMPLOYEE_ID).build())
                .state(OrderState.DONE)  // Not in PREPARING state
                .build();

        when(authorizationSecurityPort.authorize(anyString())).thenReturn(employee);
        when(orderPersistencePort.findById(1L)).thenReturn(order);

        // When & Then
        assertThrows(OrderIsNotInPreparationStateException.class, () -> orderUseCase.setOrderAsDone(1L));
    }

    @Test
    void shouldThrowNotificationWasNotSentException_WhenNotificationFails() {
        // Given
        AuthorizedUser employee = AuthorizedUser.builder()
                .id(EMPLOYEE_ID)
                .role(RoleName.EMPLOYEE)
                .build();

        Order order = Order.builder()
                .id(1L)
                .assignedEmployee(Employee.builder().id(EMPLOYEE_ID).build())
                .state(OrderState.PREPARING)
                .customerId(CUSTOMER_ID)
                .securityPin(SECURITY_PIN)
                .build();

        User customer = User.builder()
                .name(CUSTOMER_NAME)
                .lastname(CUSTOMER_LASTNAME)
                .phone(CUSTOMER_PHONE)
                .build();

        when(authorizationSecurityPort.authorize(anyString())).thenReturn(employee);
        when(orderPersistencePort.findById(1L)).thenReturn(order);
        when(userPersistencePort.getUser(CUSTOMER_ID)).thenReturn(customer);
        doThrow(new RuntimeException("Notification failure")).when(notificationSenderPort).sendNotification(any(Notification.class));

        // When & Then
        assertThrows(NotificationWasNotSentException.class, () -> orderUseCase.setOrderAsDone(1L));
    }

}