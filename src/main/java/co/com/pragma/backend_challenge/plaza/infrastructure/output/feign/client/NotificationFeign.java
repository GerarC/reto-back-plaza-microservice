package co.com.pragma.backend_challenge.plaza.infrastructure.output.feign.client;

import co.com.pragma.backend_challenge.plaza.infrastructure.configuration.feign.FeignClientConfiguration;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.feign.dto.request.NotificationRequest;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.feign.dto.response.NotificationResponse;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.feign.util.FeignConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = FeignConstants.NOTIFICATION_CLIENT_NAME,
        url = "${mall.notification.base-url}/notifications" ,
        configuration = FeignClientConfiguration.class
)
public interface NotificationFeign {
    @PostMapping
    NotificationResponse sendNotification(@RequestBody NotificationRequest request);
}
