package co.com.pragma.backend_challenge.plaza.domain.usecase;

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
import co.com.pragma.backend_challenge.plaza.domain.util.enums.RoleName;
import co.com.pragma.backend_challenge.plaza.domain.util.pagination.DomainPage;
import co.com.pragma.backend_challenge.plaza.domain.util.pagination.PaginationData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RestaurantUseCaseTest {
    @Mock
    private UserPersistencePort userPersistencePort;

    @Mock
    private RestaurantPersistencePort restaurantPersistencePort;

    @Mock
    private AuthorizationSecurityPort authorizationSecurityPort;

    @Mock
    private EmployeePersistencePort employeePersistencePort;

    @Mock
    private DishPersistencePort dishPersistencePort;

    @InjectMocks
    RestaurantUseCase restaurantUseCase;

    public static final String TOKEN = "Bearer any-token-buh";
    private static final String CATEGORY_MAIN_COURSE = "Main Course";
    private static final String CATEGORY_DESSERTS = "Desserts";

    public static final String DIFFERENT_OWNER_ID = "different-owner";
    private static final String RESTAURANT_ID = "restaurant-id";
    private static final String RESTAURANT_NIT = "987654321";
    private static final String RESTAURANT_OWNER_ID = "owner123";
    private static final String RESTAURANT_NAME = "Mock Restaurant";
    private static final String RESTAURANT_ADDRESS = "123 Fake Street";
    private static final String RESTAURANT_PHONE = "555-1234";
    private static final String RESTAURANT_LOGO_URL = "http://example.com/logo.png";

    private static final Restaurant mockRestaurant = new Restaurant.RestaurantBuilder()
            .nit(RESTAURANT_NIT)
            .ownerId(RESTAURANT_OWNER_ID)
            .name(RESTAURANT_NAME)
            .address(RESTAURANT_ADDRESS)
            .phone(RESTAURANT_PHONE)
            .logoUrl(RESTAURANT_LOGO_URL)
            .build();

    private static final Restaurant expectedRestaurant = new Restaurant.RestaurantBuilder()
            .id(RESTAURANT_ID)
            .nit(RESTAURANT_NIT)
            .ownerId(RESTAURANT_OWNER_ID)
            .name(RESTAURANT_NAME)
            .address(RESTAURANT_ADDRESS)
            .phone(RESTAURANT_PHONE)
            .logoUrl(RESTAURANT_LOGO_URL)
            .build();


    @BeforeEach
    void setUp() {
        TokenHolder.setToken(TOKEN);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createRestaurant() {
        when(userPersistencePort.isOwner(any())).thenReturn(true);
        when(restaurantPersistencePort.findByNit(any())).thenReturn(null);
        when(restaurantPersistencePort.saveRestaurant(any())).thenReturn(expectedRestaurant);

        Restaurant restaurant = restaurantUseCase.createRestaurant(mockRestaurant);

        verify(userPersistencePort).isOwner(any());
        verify(restaurantPersistencePort).findByNit(any());
        verify(restaurantPersistencePort).saveRestaurant(any());
        assertEquals(RESTAURANT_ID, restaurant.getId());
        assertEquals(restaurant.getLogoUrl(), mockRestaurant.getLogoUrl());
    }

    @Test
    void createRestaurant_userIsNotOwner() {
        when(userPersistencePort.isOwner(any())).thenReturn(false);

        assertThrows(UserRoleMustBeOwnerException.class, () -> restaurantUseCase.createRestaurant(mockRestaurant));

        verify(restaurantPersistencePort, times(0)).saveRestaurant(any());
    }

    @Test
    void createRestaurant_restaurantAlreadyExists() {
        when(userPersistencePort.isOwner(any())).thenReturn(true);
        when(restaurantPersistencePort.findByNit(any())).thenReturn(expectedRestaurant);

        assertThrows(EntityAlreadyExistsException.class, () -> restaurantUseCase.createRestaurant(mockRestaurant));

        verify(restaurantPersistencePort, times(0)).saveRestaurant(any());
    }

    @Test
    void registerEmployee_Success() {
        when(restaurantPersistencePort.findById(any())).thenReturn(mockRestaurant);
        when(authorizationSecurityPort.authorize(any())).thenReturn(
                AuthorizedUser.builder().id(RESTAURANT_OWNER_ID).role(RoleName.OWNER).build()
        );
        when(employeePersistencePort.saveEmployee(any())).thenReturn(
                Employee.builder().restaurant(mockRestaurant).build()
        );

        Employee employee = Employee.builder().restaurant(mockRestaurant).build();

        Employee registeredEmployee = restaurantUseCase.registerEmployee(employee);

        // Assert
        verify(restaurantPersistencePort).findById(any());
        verify(authorizationSecurityPort).authorize(any());
        verify(employeePersistencePort).saveEmployee(any());
        assertNotNull(registeredEmployee);
    }

    @Test
    void registerEmployee_RestaurantNotFound() {
        when(restaurantPersistencePort.findById(any())).thenReturn(null);

        Employee employee = Employee.builder().restaurant(mockRestaurant).build();

        assertThrows(EntityNotFoundException.class, () -> restaurantUseCase.registerEmployee(employee));
        verify(employeePersistencePort, never()).saveEmployee(any());
    }

    @Test
    void registerEmployee_UserNotOwner() {
        when(restaurantPersistencePort.findById(any())).thenReturn(mockRestaurant);
        when(authorizationSecurityPort.authorize(any())).thenReturn(
                AuthorizedUser.builder().id(DIFFERENT_OWNER_ID).role(RoleName.OWNER).build()
        );

        Employee employee = Employee.builder().restaurant(mockRestaurant).build();

        assertThrows(RestaurantDoesNotBelongToUserException.class, () -> restaurantUseCase.registerEmployee(employee));
        verify(employeePersistencePort, never()).saveEmployee(any());
    }

    @Test
    void findPage_ShouldReturnDomainPageOfRestaurants() {
        PaginationData paginationData = PaginationData.builder().build();
        DomainPage<Restaurant> expectedPage = DomainPage.<Restaurant>builder().build();

        when(restaurantPersistencePort.findAll(any(PaginationData.class))).thenReturn(expectedPage);

        DomainPage<Restaurant> result = restaurantUseCase.findPage(paginationData);

        verify(restaurantPersistencePort).findAll(paginationData);
        assertEquals(expectedPage, result);
        assertEquals(DomainConstants.NAME_FIELD, paginationData.getColumn());
    }

    @Test
    void findDishesOfRestaurant_ShouldReturnDomainPageOfDishes() {
        PaginationData paginationData = PaginationData.builder().build();
        DomainPage<Dish> expectedPage = DomainPage.<Dish>builder().build();

        when(dishPersistencePort.findAll(any(), any())).thenReturn(expectedPage);
        when(restaurantPersistencePort.findById(any())).thenReturn(mockRestaurant);

        DomainPage<Dish> result = restaurantUseCase.findDishesOfRestaurant(RESTAURANT_ID, CATEGORY_MAIN_COURSE, paginationData);

        verify(dishPersistencePort).findAll(any(), eq(paginationData));
        assertEquals(expectedPage, result);
    }

    @Test
    void findDishesOfRestaurant_ShouldHandleEmptyResults() {
        PaginationData paginationData = PaginationData.builder().build();
        DomainPage<Dish> emptyPage = DomainPage.<Dish>builder().build();
        emptyPage.setContent(List.of());

        when(dishPersistencePort.findAll(any(), any())).thenReturn(emptyPage);
        when(restaurantPersistencePort.findById(any())).thenReturn(mockRestaurant);

        DomainPage<Dish> result = restaurantUseCase.findDishesOfRestaurant(RESTAURANT_ID, CATEGORY_DESSERTS, paginationData);

        assertNotNull(result);
        assertTrue(result.getContent().isEmpty());
    }
}