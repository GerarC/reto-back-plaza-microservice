package co.com.pragma.backend_challenge.plaza.domain.api.security;

import co.com.pragma.backend_challenge.plaza.domain.model.security.AuthorizedUser;

public interface AuthorizationServicePort {
    AuthorizedUser authorize(String token);
}
