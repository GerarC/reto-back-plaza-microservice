package co.com.pragma.backend_challenge.plaza.infrastructure.output.feign.util;

import co.com.pragma.backend_challenge.plaza.domain.util.annotation.Generated;

@Generated
public class FeignConstants {

    private FeignConstants() throws IllegalAccessException {
        throw new IllegalAccessException("Utility Class");
    }

    public static final String USER_CLIENT_NAME = "USER-CLIENT";
    public static final String AUTH_CLIENT_NAME = "AUTH-CLIENT";
    public static final String NOTIFICATION_CLIENT_NAME = "NOTIFICATION-CLIENT";
    public static final String ORDER_REPORT_CLIENT = "ORDER-REPORT-CLIENT";
}
