package co.com.pragma.backend_challenge.plaza.infrastructure.output.feign.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationRequest {
    private String receiver;
    private String message;
}
