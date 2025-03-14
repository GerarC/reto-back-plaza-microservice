package co.com.pragma.backend_challenge.plaza.infrastructure.input.rest.v1;

import co.com.pragma.backend_challenge.plaza.application.dto.request.DishRequest;
import co.com.pragma.backend_challenge.plaza.application.dto.response.DishResponse;
import co.com.pragma.backend_challenge.plaza.application.dto.response.RestaurantResponse;
import co.com.pragma.backend_challenge.plaza.application.handler.DishHandler;
import co.com.pragma.backend_challenge.plaza.infrastructure.configuration.advisor.response.ExceptionResponse;
import co.com.pragma.backend_challenge.plaza.infrastructure.configuration.advisor.response.ValidationExceptionResponse;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/dishes")
@RequiredArgsConstructor
public class DishController {
    private final DishHandler dishHandler;


    @Operation(summary = RestConstants.SWAGGER_SUMMARY_CREATE_DISH)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = RestConstants.SWAGGER_CODE_CREATED,
                    description = RestConstants.SWAGGER_DESCRIPTION_CREATED_DISH,
                    content =  @Content(schema = @Schema(implementation = DishResponse.class))
            ),
            @ApiResponse(
                    responseCode = RestConstants.SWAGGER_CODE_NOT_FOUND,
                    description = RestConstants.SWAGGER_ERROR_RESTAURANT_DOES_NOT_FOUND,
                    content =  @Content(schema = @Schema(implementation = ExceptionResponse.class))
            ),
            @ApiResponse(
                    responseCode = RestConstants.SWAGGER_CODE_BAD_REQUEST,
                    description = RestConstants.SWAGGER_ERROR_VALIDATIONS_DO_NOT_PASS,
                    content =  @Content(schema = @Schema(implementation = ValidationExceptionResponse.class))
            ),
    })
    @PostMapping
    public ResponseEntity<DishResponse> createDish(@RequestBody @Valid DishRequest dishRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                dishHandler.createDish(dishRequest)
        );
    }
}
