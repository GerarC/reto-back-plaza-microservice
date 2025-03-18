package co.com.pragma.backend_challenge.plaza.domain.usecase;

import co.com.pragma.backend_challenge.plaza.domain.exception.EntityNotFoundException;
import co.com.pragma.backend_challenge.plaza.domain.exception.RestaurantDoesNotBelongToUserException;
import co.com.pragma.backend_challenge.plaza.domain.model.Dish;
import co.com.pragma.backend_challenge.plaza.domain.model.DishCategory;
import co.com.pragma.backend_challenge.plaza.domain.model.Restaurant;
import co.com.pragma.backend_challenge.plaza.domain.model.security.AuthorizedUser;
import co.com.pragma.backend_challenge.plaza.domain.spi.persistence.DishCategoryPersistecePort;
import co.com.pragma.backend_challenge.plaza.domain.spi.persistence.DishPersistencePort;
import co.com.pragma.backend_challenge.plaza.domain.spi.persistence.RestaurantPersistencePort;
import co.com.pragma.backend_challenge.plaza.domain.spi.security.AuthorizationSecurityPort;
import co.com.pragma.backend_challenge.plaza.domain.util.TokenHolder;
import co.com.pragma.backend_challenge.plaza.domain.util.enums.DishState;
import co.com.pragma.backend_challenge.plaza.domain.util.enums.RoleName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DishUseCaseTest {

    @Mock
    private DishPersistencePort dishPersistencePort;
    @Mock
    private DishCategoryPersistecePort dishCategoryPersistecePort;
    @Mock
    private RestaurantPersistencePort restaurantPersistencePort;
    @Mock
    private AuthorizationSecurityPort authorizationSecurityPort;

    @InjectMocks
    private DishUseCase dishUseCase;


    private static final String USER_ID = "user-id";
    private static final RoleName USER_ROLE = RoleName.OWNER;
    private static final String USER_TOKEN = "user-authorization-token";
    public static final String ANOTHER_USER_ID = "another-user";

    private final AuthorizedUser mockUser = AuthorizedUser.builder()
            .role(USER_ROLE)
            .token(USER_TOKEN)
            .id(USER_ID)
            .build();

    private static final String RESTAURANT_ID = "restaurant-id";
    private static final String RESTAURANT_NIT = "987654321";
    private static final String RESTAURANT_NAME = "Mock Restaurant";
    private static final String RESTAURANT_ADDRESS = "123 Fake Street";
    private static final String RESTAURANT_PHONE = "555-1234";
    private static final String RESTAURANT_LOGO_URL = "http://example.com/logo.png";

    private static final Restaurant mockRestaurant = new Restaurant.RestaurantBuilder()
            .id(RESTAURANT_ID)
            .nit(RESTAURANT_NIT)
            .ownerId(USER_ID)
            .name(RESTAURANT_NAME)
            .address(RESTAURANT_ADDRESS)
            .phone(RESTAURANT_PHONE)
            .logoUrl(RESTAURANT_LOGO_URL)
            .build();

    private static final Long DISH_CATEGORY_ID = 1L;
    private static final String DISH_CATEGORY_DESCRIPTION = "Pasta";

    private static final DishCategory mockDishCategory = DishCategory.builder()
            .id(DISH_CATEGORY_ID)
            .description(DISH_CATEGORY_DESCRIPTION)
            .build();

    private static final Long DISH_ID = 101L;
    private static final String DISH_NAME = "Spaghetti Carbonara";
    private static final String DISH_DESCRIPTION = "Classic Italian pasta with creamy sauce, pancetta, and Parmesan.";
    private static final Long DISH_PRICE = 1499L; // Price in cents: $14.99
    private static final String DISH_IMAGE_URL = "http://example.com/spaghetti-carbonara.jpg";
    private static final DishState DISH_STATE = DishState.ACTIVE;

    private static final String DISH_NEW_DESCRIPTION = "New Description";
    private static final Long DISH_NEW_PRICE = 20000000L;

    private static final Dish mockDish = Dish.builder()
            .id(DISH_ID)
            .restaurant(mockRestaurant)
            .name(DISH_NAME)
            .description(DISH_DESCRIPTION)
            .price(DISH_PRICE)
            .category(mockDishCategory)
            .imageUrl(DISH_IMAGE_URL)
            .state(DISH_STATE)
            .build();

    private static final Dish expectedDish = Dish.builder()
            .id(DISH_ID)
            .restaurant(mockRestaurant)
            .name(DISH_NAME)
            .description(DISH_DESCRIPTION)
            .price(DISH_PRICE)
            .category(mockDishCategory)
            .imageUrl(DISH_IMAGE_URL)
            .state(DISH_STATE)
            .build();


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        TokenHolder.setToken(USER_TOKEN);
    }

    @Test
    void createDish() {
        when(dishCategoryPersistecePort.findByDescription(any())).thenReturn(mockDishCategory);
        when(restaurantPersistencePort.findById(any())).thenReturn(mockRestaurant);
        when(dishPersistencePort.saveDish(any())).thenReturn(expectedDish);
        when(authorizationSecurityPort.authorize(any())).thenReturn(mockUser);

        Dish dish = dishUseCase.createDish(mockDish);

        verify(dishCategoryPersistecePort).findByDescription(any());
        verify(restaurantPersistencePort).findById(any());
        verify(dishPersistencePort).saveDish(any());
        assertEquals(DISH_ID, dish.getId());
    }

    @Test
    void createDish_NewCategory() {
        when(dishCategoryPersistecePort.findByDescription(any())).thenReturn(null);
        when(dishCategoryPersistecePort.saveCategory(any())).thenReturn(mockDishCategory);
        when(restaurantPersistencePort.findById(any())).thenReturn(mockRestaurant);
        when(authorizationSecurityPort.authorize(any())).thenReturn(mockUser);
        when(dishPersistencePort.saveDish(any())).thenReturn(expectedDish);

        Dish dish = dishUseCase.createDish(mockDish);

        verify(dishCategoryPersistecePort).findByDescription(any());
        verify(restaurantPersistencePort).findById(any());
        verify(dishPersistencePort).saveDish(any());
        assertEquals(DISH_ID, dish.getId());
    }

    @Test
    void createDish_noRestaurant() {
        when(dishCategoryPersistecePort.findByDescription(any())).thenReturn(mockDishCategory);
        when(restaurantPersistencePort.findById(any())).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () ->
            dishUseCase.createDish(mockDish)
        );
        verify(dishPersistencePort, times(0)).saveDish(any());
    }

    @Test
    void modifyDish(){
        Dish modifiedInfoDish = Dish.builder()
                .description(DISH_NEW_DESCRIPTION)
                .price(DISH_NEW_PRICE)
                .build();
        final Dish expectedModifiedDish = Dish.builder()
                .id(DISH_ID)
                .restaurant(mockRestaurant)
                .name(DISH_NAME)
                .description(DISH_NEW_DESCRIPTION)
                .price(DISH_NEW_PRICE)
                .category(mockDishCategory)
                .imageUrl(DISH_IMAGE_URL)
                .state(DISH_STATE)
                .build();
        when(dishPersistencePort.findById(any())).thenReturn(mockDish);
        when(restaurantPersistencePort.findById(any())).thenReturn(mockRestaurant);
        when(authorizationSecurityPort.authorize(any())).thenReturn(mockUser);
        when(dishPersistencePort.saveDish(any())).thenReturn(expectedModifiedDish);

        Dish modifiedDish = dishUseCase.modifyDish(DISH_ID, modifiedInfoDish);

        assertEquals(DISH_NEW_DESCRIPTION, modifiedDish.getDescription());
        assertEquals(DISH_NEW_PRICE, modifiedDish.getPrice());
    }

    @Test
    void modifyDish_dishNotFound(){
        when(dishPersistencePort.findById(any())).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () ->
                dishUseCase.modifyDish(DISH_ID, mockDish)
        );
        verify(dishPersistencePort, times(0)).saveDish(any());
    }

    @Test
    void changeDishState_Success() {
        expectedDish.setState(DishState.INACTIVE);
        when(dishPersistencePort.findById(any())).thenReturn(mockDish);
        when(restaurantPersistencePort.findById(any())).thenReturn(mockRestaurant);
        when(authorizationSecurityPort.authorize(any())).thenReturn(mockUser);
        when(dishPersistencePort.saveDish(any())).thenReturn(expectedDish);

        Dish dish = dishUseCase.changeDishState(DISH_ID, DishState.INACTIVE);

        verify(dishPersistencePort).findById(any());
        verify(restaurantPersistencePort).findById(any());
        verify(dishPersistencePort).saveDish(any());
        assertEquals(DishState.INACTIVE, dish.getState());
    }

    @Test
    void changeDishState_DishNotFound() {
        when(dishPersistencePort.findById(any())).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () ->
                dishUseCase.changeDishState(DISH_ID, DishState.INACTIVE)
        );
        verify(dishPersistencePort, times(0)).saveDish(any());
    }

    @Test
    void changeDishState_UserNotOwner() {
        when(dishPersistencePort.findById(any())).thenReturn(mockDish);
        when(restaurantPersistencePort.findById(any())).thenReturn(mockRestaurant);
        when(authorizationSecurityPort.authorize(any())).thenReturn(
                AuthorizedUser.builder().id(ANOTHER_USER_ID).role(RoleName.OWNER).build()
        );

        assertThrows(RestaurantDoesNotBelongToUserException.class, () ->
                dishUseCase.changeDishState(DISH_ID, DishState.INACTIVE)
        );
        verify(dishPersistencePort, times(0)).saveDish(any());
    }

}