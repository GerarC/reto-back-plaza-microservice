package co.com.pragma.backend_challenge.plaza.domain.util;

import co.com.pragma.backend_challenge.plaza.domain.util.annotation.Generated;

@Generated
public class DomainConstants {

    private DomainConstants() {
        throw new IllegalStateException("Utility Class");
    }

    // Fields
    public static final String NAME_FIELD = "name";

    // Pagination
    public static final Integer PAGE_SIZE = 10;

    public static final String TOKEN_PREFIX = "Bearer ";

    // Error messages
    public static final String RESTAURANT_OWNER_MUST_HAVE_OWNER_ROLE = "A Restaurant Owner must have OWNER as his role";
    public static final String ENTITY_NOT_FOUND_TEMPLATE_MESSAGE = "Entity of type '%s' with id '%s' has not been found";
    public static final String ENTITY_ALREADY_EXISTS_TEMPLATE_MESSAGE = "An entity of type '%s' with id '%s' already exists";
    public static final String NOT_AUTHORIZED_ERROR_MESSAGE = "User has not authorization";
    public static final String RESTAURANT_DOES_NOT_BELONG_TO_USER = "The restaurant doesn't belong to the user";
}
