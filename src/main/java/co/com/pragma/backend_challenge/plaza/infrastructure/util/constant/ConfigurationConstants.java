package co.com.pragma.backend_challenge.plaza.infrastructure.util.constant;

import co.com.pragma.backend_challenge.plaza.domain.util.annotation.Generated;

@Generated
public class ConfigurationConstants {
    private ConfigurationConstants() {
        throw new IllegalStateException("Utility Class");
    }

    // Documentation
    public static final String OPENAPI_TERMS_OF_SERVICE = "https://swagger.io/terms/";
    public static final String OPENAPI_LICENSE_NAME = "Apache 2.0";
    public static final String OPENAPI_LICENSE_URL = "https://springdoc.org";

    // Security
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String ROLE_PREFIX = "ROLE_";
    public static final String TOKEN_PREFIX = "Bearer ";


}
