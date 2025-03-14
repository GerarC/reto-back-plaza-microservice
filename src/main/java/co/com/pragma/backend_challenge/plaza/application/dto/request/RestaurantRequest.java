package co.com.pragma.backend_challenge.plaza.application.dto.request;

import co.com.pragma.backend_challenge.plaza.application.util.AppConstants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestaurantRequest {
    @NotNull(message = AppConstants.NIT_FIELD_NOT_NULL)
    @Pattern(regexp = AppConstants.NIT_REGEX, message = AppConstants.WRONG_NIT_FORMAT)
    private String nit;

    @NotNull(message = AppConstants.OWNER_ID_FIELD_NOT_NULL)
    private String ownerId;

    @NotNull(message = AppConstants.NAME_FIELD_NOT_NULL)
    @Pattern(regexp = AppConstants.NAME_REGEX, message = AppConstants.WRONG_NAME_FORMAT)
    private String name;

    @NotNull(message = AppConstants.ADDRESS_FIELD_NOT_NULL)
    private String address;

    @NotNull(message = AppConstants.PHONE_FIELD_NOT_NULL)
    @Pattern(regexp = AppConstants.PHONE_NUMBER_REGEX, message = AppConstants.WRONG_PHONE_FORMAT)
    private String phone;

    @NotNull(message = AppConstants.LOGO_URL_FIELD_NOT_NULL)
    private String logoUrl;
}
