package co.com.pragma.backend_challenge.plaza.infrastructure.output.feign.client;

import co.com.pragma.backend_challenge.plaza.infrastructure.configuration.feign.FeignClientConfiguration;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.feign.dto.response.IsOwnerResponse;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.feign.dto.response.ShortUserResponse;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.feign.util.FeignConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = FeignConstants.USER_CLIENT_NAME,
        url = "${mall.user.base-url}/users" ,
        configuration = FeignClientConfiguration.class
)
public interface UserFeign {
    @GetMapping("/{id}/is-owner")
    IsOwnerResponse isOwner(@PathVariable String id);
    @GetMapping("/{id}/short")
    ShortUserResponse getUserShortResponse(@PathVariable String id);
}
