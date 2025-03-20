package co.com.pragma.backend_challenge.plaza.application.dto.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class OrderPinRequest {
    String securityPin;
}
