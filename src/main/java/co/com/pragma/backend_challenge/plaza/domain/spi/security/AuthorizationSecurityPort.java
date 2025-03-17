package co.com.pragma.backend_challenge.plaza.domain.spi.security;

import co.com.pragma.backend_challenge.plaza.domain.model.security.AuthorizedUser;

public interface AuthorizationSecurityPort {
    AuthorizedUser authorize(String token);
}
