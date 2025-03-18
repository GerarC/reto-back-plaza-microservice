package co.com.pragma.backend_challenge.plaza.application.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestaurantResponse {
    private String name;
    private String logoUrl;
}
