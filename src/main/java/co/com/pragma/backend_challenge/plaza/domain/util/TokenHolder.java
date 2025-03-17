package co.com.pragma.backend_challenge.plaza.domain.util;

public class TokenHolder {
    private TokenHolder() {
        throw new UnsupportedOperationException("TokenContainer is a utility class and cannot be instantiated");
    }

    // add id to token
    private static String token;

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        TokenHolder.token = token;
    }
}