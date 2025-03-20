package co.com.pragma.backend_challenge.plaza.domain.exception;

import co.com.pragma.backend_challenge.plaza.domain.util.DomainConstants;

public class NotificationWasNotSentException extends RuntimeException {

    public NotificationWasNotSentException(){
        super(DomainConstants.NOTIFICATION_SERVICE_IS_NOT_AVAILABLE);
    }
}
