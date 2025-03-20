package co.com.pragma.backend_challenge.plaza.domain.spi.messaging;

import co.com.pragma.backend_challenge.plaza.domain.model.messaging.Notification;

public interface NotificationSenderPort {
    boolean sendNotification(Notification notification);
}
