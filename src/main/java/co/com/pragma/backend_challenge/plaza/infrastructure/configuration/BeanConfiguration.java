package co.com.pragma.backend_challenge.plaza.infrastructure.configuration;

import co.com.pragma.backend_challenge.plaza.domain.api.DishServicePort;
import co.com.pragma.backend_challenge.plaza.domain.api.RestaurantServicePort;
import co.com.pragma.backend_challenge.plaza.domain.api.security.AuthorizationServicePort;
import co.com.pragma.backend_challenge.plaza.domain.spi.persistence.DishCategoryPersistecePort;
import co.com.pragma.backend_challenge.plaza.domain.spi.persistence.DishPersistencePort;
import co.com.pragma.backend_challenge.plaza.domain.spi.persistence.RestaurantPersistencePort;
import co.com.pragma.backend_challenge.plaza.domain.spi.persistence.UserPersistencePort;
import co.com.pragma.backend_challenge.plaza.domain.spi.security.AuthorizationSecurityPort;
import co.com.pragma.backend_challenge.plaza.domain.usecase.DishUseCase;
import co.com.pragma.backend_challenge.plaza.domain.usecase.RestaurantUseCase;
import co.com.pragma.backend_challenge.plaza.domain.usecase.security.AuthorizationUseCase;
import co.com.pragma.backend_challenge.plaza.domain.util.annotation.Generated;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Generated
@Configuration
public class BeanConfiguration {
    @Bean
    public RestaurantServicePort restaurantServicePort(
            RestaurantPersistencePort restaurantPersistencePort,
            UserPersistencePort userPersistencePort) {
        return new RestaurantUseCase(
                restaurantPersistencePort,
                userPersistencePort
        );
    }

    @Bean
    public DishServicePort dishServicePort(
            DishPersistencePort dishPersistencePort,
            DishCategoryPersistecePort dishCategoryPersistecePort,
            RestaurantPersistencePort restaurantPersistencePort
    ) {
        return new DishUseCase(
                dishPersistencePort,
                dishCategoryPersistecePort,
                restaurantPersistencePort
        );
    }

    @Bean
    public AuthorizationServicePort authorizationServicePort(
            AuthorizationSecurityPort authorizationSecurityPort
    ){
        return new AuthorizationUseCase(authorizationSecurityPort);
    }


    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }
}
