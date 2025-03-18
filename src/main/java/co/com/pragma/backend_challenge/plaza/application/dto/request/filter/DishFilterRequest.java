package co.com.pragma.backend_challenge.plaza.application.dto.request.filter;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DishFilterRequest {
    private String category;
}
