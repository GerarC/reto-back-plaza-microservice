package co.com.pragma.backend_challenge.plaza.application.dto.request.dish;

import co.com.pragma.backend_challenge.plaza.application.util.AppConstants;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PatchDishRequest {
    private String description;

    @Positive(message = AppConstants.PRICE_MUST_BE_POSITIVE)
    private Long price;
}
