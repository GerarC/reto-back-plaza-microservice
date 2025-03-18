package co.com.pragma.backend_challenge.plaza.application.dto.request;

import co.com.pragma.backend_challenge.plaza.domain.util.enums.DishState;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DishStateRequest {
    private DishState state;
}
