package co.com.pragma.backend_challenge.plaza.application.dto.response;

import co.com.pragma.backend_challenge.plaza.domain.util.enums.DishState;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DishResponse {
    private Long id;
    private String restaurantId;
    private String name;
    private String description;
    private Long price;
    private String category;
    private String imageUrl;
    private DishState state;
}
