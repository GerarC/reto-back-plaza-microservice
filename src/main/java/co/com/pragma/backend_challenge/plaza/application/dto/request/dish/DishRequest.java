package co.com.pragma.backend_challenge.plaza.application.dto.request.dish;

import co.com.pragma.backend_challenge.plaza.application.util.AppConstants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Data
@Builder
public class DishRequest {
    @NotNull(message = AppConstants.RESTAURANT_ID_FIELD_NOT_NULL)
    private String restaurantId;

    @NotNull(message = AppConstants.NAME_FIELD_NOT_NULL)
    private String name;

    @NotNull(message = AppConstants.DESCRIPTION_FIELD_NOT_NULL)
    private String description;

    @NotNull(message = AppConstants.PRICE_FIELD_NOT_NULL)
    @Positive(message = AppConstants.PRICE_MUST_BE_POSITIVE)
    private Long price;

    @NotNull(message = AppConstants.CATEGORY_FIELD_NOT_NULL)
    private String category;

    @NotNull(message = AppConstants.IMAGE_URL_FIELD_NOT_NULL)
    private String imageUrl;
}
