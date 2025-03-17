package co.com.pragma.backend_challenge.plaza.domain.usecase.security;

import co.com.pragma.backend_challenge.plaza.domain.exception.NotAuthorizedException;
import co.com.pragma.backend_challenge.plaza.domain.model.security.AuthorizedUser;
import co.com.pragma.backend_challenge.plaza.domain.spi.security.AuthorizationSecurityPort;
import co.com.pragma.backend_challenge.plaza.domain.util.enums.RoleName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


class AuthorizationUseCaseTest {

    @Mock
    private AuthorizationSecurityPort authorizationSecurityPort;

    @InjectMocks
    AuthorizationUseCase authorizationUseCase;

    private static final String USER_ID = "user-id";
    private static final RoleName USER_ROLE = RoleName.ADMIN;
    private static final String USER_TOKEN = "user-authorization-token";

    private final AuthorizedUser mockUser = AuthorizedUser.builder()
            .role(USER_ROLE)
            .token(USER_TOKEN)
            .id(USER_ID)
            .build();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void authorize() {
        when(authorizationSecurityPort.authorize(USER_TOKEN)).thenReturn(mockUser);

        AuthorizedUser user = authorizationUseCase.authorize(USER_TOKEN);

        assertEquals(user.getId(), mockUser.getId());
    }

    @Test
    void authorize_error() {
        when(authorizationSecurityPort.authorize(USER_TOKEN)).thenThrow(RuntimeException.class);

        assertThrows(NotAuthorizedException.class, ()-> authorizationUseCase.authorize(USER_TOKEN));
    }
}