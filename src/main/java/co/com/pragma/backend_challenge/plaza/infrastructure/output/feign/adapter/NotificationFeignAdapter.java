package co.com.pragma.backend_challenge.plaza.infrastructure.output.feign.adapter;

import co.com.pragma.backend_challenge.plaza.domain.model.messaging.Notification;
import co.com.pragma.backend_challenge.plaza.domain.spi.messaging.NotificationSenderPort;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.feign.client.NotificationFeign;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.feign.dto.request.NotificationRequest;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.feign.mapper.request.NotificationRequestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationFeignAdapter implements NotificationSenderPort {
    private final NotificationFeign notificationFeign;
    private final NotificationRequestMapper notificationRequestMapper;

    @Override
    public boolean sendNotification(Notification notification) {
        NotificationRequest request = notificationRequestMapper.toRequest(notification);
        return notificationFeign.sendNotification(request).isSent();
    }
}
