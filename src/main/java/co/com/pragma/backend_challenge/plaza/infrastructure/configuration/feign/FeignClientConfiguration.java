package co.com.pragma.backend_challenge.plaza.infrastructure.configuration.feign;

import co.com.pragma.backend_challenge.plaza.domain.util.TokenHolder;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.feign.client.AuthFeign;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.feign.client.UserFeign;
import co.com.pragma.backend_challenge.plaza.infrastructure.util.constant.ConfigurationConstants;
import feign.Feign;
import feign.Logger;
import feign.QueryMapEncoder;
import feign.RequestInterceptor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
@EnableFeignClients(
        clients = {
                AuthFeign.class,
                UserFeign.class
        }
)
public class FeignClientConfiguration {
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public QueryMapEncoder feignQueryMapEncoder(FeignQueryBuilder feignQueryBuilder) {
        Feign.builder().queryMapEncoder(feignQueryBuilder);
        return feignQueryBuilder;
    }

    @Bean
    public RequestInterceptor feignInterceptor() {
        return requestTemplate -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.isAuthenticated()) {
                requestTemplate.header(
                        ConfigurationConstants.AUTHORIZATION_HEADER,
                        TokenHolder.getToken()
                );
            }
        };
    }
}