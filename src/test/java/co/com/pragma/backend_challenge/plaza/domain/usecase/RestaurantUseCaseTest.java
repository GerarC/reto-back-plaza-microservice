package co.com.pragma.backend_challenge.plaza.domain.usecase;

import co.com.pragma.backend_challenge.plaza.domain.exception.EntityAlreadyExistsException;
import co.com.pragma.backend_challenge.plaza.domain.exception.UserRoleMustBeOwnerException;
import co.com.pragma.backend_challenge.plaza.domain.model.Restaurant;
import co.com.pragma.backend_challenge.plaza.domain.spi.persistence.RestaurantPersistencePort;
import co.com.pragma.backend_challenge.plaza.domain.spi.persistence.UserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RestaurantUseCaseTest {

    @Mock
    private UserPersistencePort userPersistencePort;

    @Mock
    private RestaurantPersistencePort restaurantPersistencePort;

    @InjectMocks
    RestaurantUseCase restaurantUseCase;

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
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createRestaurant() {
        // Assert
        when(userPersistencePort.isOwner(any())).thenReturn(true);
        when(restaurantPersistencePort.findByNit(any())).thenReturn(null);
        when(restaurantPersistencePort.saveRestaurant(any())).thenReturn(expectedRestaurant);

        // Act
        Restaurant restaurant = restaurantUseCase.createRestaurant(mockRestaurant);

        verify(userPersistencePort).isOwner(any());
        verify(restaurantPersistencePort).findByNit(any());
        verify(restaurantPersistencePort).saveRestaurant(any());
        assertEquals(RESTAURANT_ID, restaurant.getId());
        assertEquals(restaurant.getLogoUrl(), mockRestaurant.getLogoUrl());
    }

    @Test
    void createRestaurant_userIsNotOwner() {
        // Assert
        when(userPersistencePort.isOwner(any())).thenReturn(false);

        // Act
        assertThrows(UserRoleMustBeOwnerException.class, () -> restaurantUseCase.createRestaurant(mockRestaurant));

        verify(restaurantPersistencePort, times(0)).saveRestaurant(any());
    }

    @Test
    void createRestaurant_restaurantAlreadyExists() {
        // Assert
        when(userPersistencePort.isOwner(any())).thenReturn(true);
        when(restaurantPersistencePort.findByNit(any())).thenReturn(expectedRestaurant);

        // Act
        assertThrows(EntityAlreadyExistsException.class, () -> restaurantUseCase.createRestaurant(mockRestaurant));

        verify(restaurantPersistencePort, times(0)).saveRestaurant(any());
    }
}