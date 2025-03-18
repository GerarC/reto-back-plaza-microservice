package co.com.pragma.backend_challenge.plaza.application.dto.request;

import co.com.pragma.backend_challenge.plaza.application.util.AppConstants;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeRequest {
    @NotNull(message = AppConstants.EMPLOYEE_ID_FIELD_NOT_NULL)
    private String id;
    @NotNull(message = AppConstants.RESTAURANT_ID_FIELD_NOT_NULL)
    private String restaurantId;
}
