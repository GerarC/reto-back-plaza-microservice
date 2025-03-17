package co.com.pragma.backend_challenge.plaza.infrastructure.output.feign.client;

import co.com.pragma.backend_challenge.plaza.infrastructure.configuration.feign.FeignClientConfiguration;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.feign.dto.response.AuthorizationResponse;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.feign.util.FeignConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = FeignConstants.AUTH_CLIENT_NAME,
        url = "${mall.user.base-url}/auth" ,
        configuration = FeignClientConfiguration.class
)
public interface AuthFeign {
    @GetMapping("/authorize")
    AuthorizationResponse authorize(@RequestParam String token);
}
