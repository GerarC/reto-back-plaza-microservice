package co.com.pragma.backend_challenge.plaza.infrastructure.util.constant;


import co.com.pragma.backend_challenge.plaza.domain.util.annotation.Generated;

@Generated
public class RestConstants {


    public static final String SWAGGER_ERROR_USER_ALREADY_HAS_A_PROCESSING_ORDER = "Customer already has a processing order currently";

    private RestConstants() {
        throw new IllegalStateException("Utility Class");
    }
    // API CODES
    public static final String SWAGGER_CODE_CREATED = "201";
    public static final String SWAGGER_CODE_OK = "200";
    public static final String SWAGGER_CODE_BAD_REQUEST = "400";
    public static final String SWAGGER_CODE_UNAUTHORIZED = "401";
    public static final String SWAGGER_CODE_NOT_FOUND = "404";
    public static final String SWAGGER_CODE_CONFLICT = "409";

    // Validations
    public static final String SWAGGER_ERROR_VALIDATIONS_DO_NOT_PASS = "Validations don't pass";
    public static final String SWAGGER_ERROR_USER_IS_NOT_RESTAURANT_OWNER = "User who's trying to patch an object that doesn't belong to they";

    // HOME
    public static final String SWAGGER_SUMMARY_GET_HOME = "And endpoint to test if app is running";

    // Restaurant
    public static final String SWAGGER_SUMMARY_CREATE_RESTAURANT = "Creates a new restaurant";
    public static final String SWAGGER_DESCRIPTION_CREATED_RESTAURANT = "Restaurant has been created successfully";
    public static final String SWAGGER_ERROR_USER_IS_NOT_OWNER = "Given user has no Owner role";
    public static final String SWAGGER_ERROR_USER_DOES_NOT_EXISTS = "Given Id doesn't belong to any existent user";
    public static final String SWAGGER_ERROR_RESTAURANT_WITH_NIT_ALREADY_EXISTS = "A Restaurant with that NIT already exists";
    public static final String SWAGGER_SUMMARY_REGISTER_EMPLOYEE = "Register relation between Employee and Restaurant";
    public static final String SWAGGER_DESCRIPTION_EMPLOYEE_RELATION_REGISTERED = "Relation between employee and restaurant has been registered successfully";
    public static final String SWAGGER_SUMMARY_FIND_PAGE_RESTAURANT = "Search all restaurants, and retrieve as page";
    public static final String SWAGGER_DESCRIPTION_FOUND_PAGE_RESTAURANT = "restaurant has been found";
    public static final String SWAGGER_SUMMARY_FIND_RESTAURANT_DISHES = "Search dishes of given restaurant";
    public static final String SWAGGER_DESCRIPTION_FOUND_RESTAURANT_DISHES = "Dishes of that restaurant where found";

    // Dish
    public static final String SWAGGER_SUMMARY_CREATE_DISH = "Creates a new dish for a restaurant";
    public static final String SWAGGER_DESCRIPTION_CREATED_DISH = "The dish has been created";
    public static final String SWAGGER_ERROR_RESTAURANT_DOES_NOT_FOUND = "A restaurant with that Id doesn't exists";
    public static final String SWAGGER_SUMMARY_MODIFY_DISH = "Modify price and description of a dish";
    public static final String SWAGGER_DESCRIPTION_MODIFIED_DISH = "The dish has been modified satisfactorily";
    public static final String SWAGGER_ERROR_DISH_NOT_FOUND = "Desired dish hasn't been found";
    public static final String SWAGGER_SUMMARY_CHANGED_DISH_STATE = "Change state of the given dish";
    public static final String SWAGGER_DESCRIPTION_CHANGED_DISH_STATE = "Dish state has been changed";

    //Order
    public static final String SWAGGER_SUMMARY_CREATE_ORDER = "Creates an order after a lot of validations";
    public static final String SWAGGER_DESCRIPTION_CREATED_ORDER = "The order has been created successfully";
    public static final String SWAGGER_ERROR_DISH_DOES_NOT_FOUND = "At least one the dishes has not been found";
    public static final String SWAGGER_ERROR_DISH_DOES_NOT_BELONG_TO_RESTAURANT = "Given dish doesn't belong to the restaurant of the order";
}
