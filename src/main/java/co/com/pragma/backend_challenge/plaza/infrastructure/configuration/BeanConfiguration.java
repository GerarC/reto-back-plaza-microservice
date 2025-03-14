package co.com.pragma.backend_challenge.plaza.infrastructure.configuration;

import co.com.pragma.backend_challenge.plaza.domain.api.DishServicePort;
import co.com.pragma.backend_challenge.plaza.domain.api.RestaurantServicePort;
import co.com.pragma.backend_challenge.plaza.domain.spi.DishCategoryPersistecePort;
import co.com.pragma.backend_challenge.plaza.domain.spi.DishPersistencePort;
import co.com.pragma.backend_challenge.plaza.domain.spi.RestaurantPersistencePort;
import co.com.pragma.backend_challenge.plaza.domain.spi.UserPersistencePort;
import co.com.pragma.backend_challenge.plaza.domain.usecase.DishUseCase;
import co.com.pragma.backend_challenge.plaza.domain.usecase.RestaurantUseCase;
import co.com.pragma.backend_challenge.plaza.domain.util.annotation.Generated;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
