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

    // Notification message
    public static final String NOTIFICATION_MESSAGE_TEMPLATE = "¡Ey, %s %s! Your order is ready, you can claim it giving the next code in the local: %s";

    // Error messages
    public static final String RESTAURANT_OWNER_MUST_HAVE_OWNER_ROLE = "A Restaurant Owner must have OWNER as his role";
    public static final String ENTITY_NOT_FOUND_TEMPLATE_MESSAGE = "Entity of type '%s' with id '%s' has not been found";
    public static final String ENTITY_ALREADY_EXISTS_TEMPLATE_MESSAGE = "An entity of type '%s' with id '%s' already exists";
    public static final String NOT_AUTHORIZED_ERROR_MESSAGE = "User has not authorization over the action";
    public static final String RESTAURANT_DOES_NOT_BELONG_TO_USER_MESSAGE = "The restaurant doesn't belong to the user";
    public static final String CUSTOMER_ALREADY_HAS_A_PROCESSING_ORDER_MESSAGE = "Customer already has a processing state";
    public static final String DISH_DOES_NOT_BELONG_TO_RESTAURANT_MESSAGE = "Dish called '%s' doesn't belong to Restaurant '%s'";
    public static final String ALREADY_ASSIGNED_TO_ANOTHER_EMPLOYEE_MESSAGE = "The chosen order is assigned to another employee";
    public static final String NOTIFICATION_SERVICE_IS_NOT_AVAILABLE = "Notification service is not available, something wrong occurred, order status was not changed";
    public static final String ORDER_CANNOT_BE_SET_AS_DONE_BECAUSE_IS_NOT_BEING_PREPARED = "Order cannot be set as  Done because is not being prepared";
    public static final String ORDER_IS_NOT_DONE = "The order is not done, you can give it to the customer";
    public static final String SECURITY_PIN_DOES_NOT_MATCH = "Given security pin doesn't match with this order";
    public static final String ORDER_DOES_NOT_BELONG_TO_THE_USER = "Order cannot be change because doesn't belong to the user";
    public static final String ORDER_CANNOT_BE_CANCELED = "Order is being prepared, cannot be canceled";


}
