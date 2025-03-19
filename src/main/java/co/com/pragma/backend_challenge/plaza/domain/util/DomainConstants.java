package co.com.pragma.backend_challenge.plaza.domain.util;

import co.com.pragma.backend_challenge.plaza.domain.util.annotation.Generated;
import co.com.pragma.backend_challenge.plaza.domain.util.enums.OrderState;

import java.util.List;

@Generated
public class DomainConstants {

    private DomainConstants() {
        throw new IllegalStateException("Utility Class");
    }

    // STUFF
    public static final Integer PIN_LENGTH = 8;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final List<OrderState> PROCESS_STATES = List.of(
            OrderState.WAITING,
            OrderState.PREPARING,
            OrderState.DONE
    );

    // Fields
    public static final String NAME_FIELD = "name";

    // Pagination
    public static final Integer PAGE_SIZE = 10;


    // Error messages
    public static final String RESTAURANT_OWNER_MUST_HAVE_OWNER_ROLE = "A Restaurant Owner must have OWNER as his role";
    public static final String ENTITY_NOT_FOUND_TEMPLATE_MESSAGE = "Entity of type '%s' with id '%s' has not been found";
    public static final String ENTITY_ALREADY_EXISTS_TEMPLATE_MESSAGE = "An entity of type '%s' with id '%s' already exists";
    public static final String NOT_AUTHORIZED_ERROR_MESSAGE = "User has not authorization over the action";
    public static final String RESTAURANT_DOES_NOT_BELONG_TO_USER_MESSAGE = "The restaurant doesn't belong to the user";
    public static final String CUSTOMER_ALREADY_HAS_A_PROCESSING_ORDER_MESSAGE = "Customer already has a processing state";
    public static final String DISH_DOES_NOT_BELONG_TO_RESTAURANT_MESSAGE = "Dish called '%s' doesn't belong to Restaurant '%s'";
    public static final String ALREADY_ASSIGNED_TO_ANOTHER_EMPLOYEE_MESSAGE = "The chosen order is already assigned to another employee";
}
