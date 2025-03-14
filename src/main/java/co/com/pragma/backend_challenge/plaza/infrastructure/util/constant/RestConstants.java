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

    // HOME
    public static final String SWAGGER_SUMMARY_GET_HOME = "And endpoint to test if app is running";

    // Restaurant
    public static final String SWAGGER_SUMMARY_CREATE_RESTAURANT = "Creates a new restaurant";
    public static final String SWAGGER_DESCRIPTION_CREATED_RESTAURANT = "Restaurant has been created successfully";
}
