package co.com.pragma.backend_challenge.plaza.infrastructure.util.constant;


import co.com.pragma.backend_challenge.plaza.domain.util.annotation.Generated;

@Generated
public class RestConstants {
    private RestConstants() {
        throw new IllegalStateException("Utility Class");
    }
    // API CODES
    public static final String SWAGGER_CODE_CREATED = "201";
    public static final String SWAGGER_CODE_OK = "200";
    public static final String SWAGGER_CODE_BAD_REQUEST = "400";
    public static final String SWAGGER_CODE_NOT_FOUND = "404";
    public static final String SWAGGER_CODE_CONFLICT = "409";

    // Validations
    public static final String SWAGGER_ERROR_VALIDATIONS_DO_NOT_PASS = "Validations don't pass";

    // HOME
    public static final String SWAGGER_SUMMARY_GET_HOME = "And endpoint to test if app is running";

    // Restaurant
    public static final String SWAGGER_SUMMARY_CREATE_RESTAURANT = "Creates a new restaurant";
    public static final String SWAGGER_DESCRIPTION_CREATED_RESTAURANT = "Restaurant has been created successfully";
    public static final String SWAGGER_ERROR_USER_IS_NOT_OWNER = "Given user has no Owner role";
    public static final String SWAGGER_ERROR_USER_DOES_NOT_EXISTS = "Given Id doesn't belong to any existent user";
    public static final String SWAGGER_ERROR_RESTAURANT_WITH_NIT_ALREADY_EXISTS = "A Restaurant with that NIT already exists";

    // Dish
    public static final String SWAGGER_SUMMARY_CREATE_DISH = "Creates a new dish for a restaurant";
    public static final String SWAGGER_DESCRIPTION_CREATED_DISH = "The dish has been created";
    public static final String SWAGGER_ERROR_RESTAURANT_DOES_NOT_FOUND = "A restaurant with that Id doesn't exists";

}
