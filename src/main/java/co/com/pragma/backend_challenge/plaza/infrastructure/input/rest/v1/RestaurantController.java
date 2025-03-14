package co.com.pragma.backend_challenge.plaza.infrastructure.input.rest.v1;


import co.com.pragma.backend_challenge.plaza.application.dto.request.RestaurantRequest;
import co.com.pragma.backend_challenge.plaza.application.dto.response.RestaurantResponse;
import co.com.pragma.backend_challenge.plaza.application.handler.RestaurantHandler;
import co.com.pragma.backend_challenge.plaza.infrastructure.util.constant.RestConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantHandler restaurantHandler;

    @Operation(summary = RestConstants.SWAGGER_SUMMARY_CREATE_RESTAURANT)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = RestConstants.SWAGGER_CODE_CREATED,
                    description = RestConstants.SWAGGER_DESCRIPTION_CREATED_RESTAURANT,
                    content =  @Content(schema = @Schema(implementation = RestaurantResponse.class))
            )
    })
    @PostMapping
    public ResponseEntity<RestaurantResponse> createRestaurant(@RequestBody @Valid RestaurantRequest restaurantRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                restaurantHandler.createRestaurant(restaurantRequest)
        );
    }
}
