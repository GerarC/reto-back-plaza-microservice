package co.com.pragma.backend_challenge.plaza.infrastructure.input.rest.v1;

import co.com.pragma.backend_challenge.plaza.application.dto.request.order.OrderRequest;
import co.com.pragma.backend_challenge.plaza.application.dto.response.DishResponse;
import co.com.pragma.backend_challenge.plaza.application.dto.response.order.OrderCreatedResponse;
import co.com.pragma.backend_challenge.plaza.application.handler.OrderHandler;
import co.com.pragma.backend_challenge.plaza.infrastructure.configuration.advisor.response.ExceptionResponse;
import co.com.pragma.backend_challenge.plaza.infrastructure.configuration.advisor.response.ValidationExceptionResponse;
import co.com.pragma.backend_challenge.plaza.infrastructure.util.constant.RestConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderHandler orderHandler;

    @Operation(summary = RestConstants.SWAGGER_SUMMARY_CREATE_DISH)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = RestConstants.SWAGGER_CODE_CREATED,
                    description = RestConstants.SWAGGER_DESCRIPTION_CREATED_DISH,
                    content =  @Content(schema = @Schema(implementation = OrderCreatedResponse.class))
            ),
            @ApiResponse(
                    responseCode = RestConstants.SWAGGER_CODE_NOT_FOUND,
                    description = RestConstants.SWAGGER_ERROR_RESTAURANT_DOES_NOT_FOUND,
                    content =  @Content(schema = @Schema(implementation = ExceptionResponse.class))
            ),
            @ApiResponse(
                    responseCode = RestConstants.SWAGGER_CODE_NOT_FOUND,
                    description = RestConstants.SWAGGER_ERROR_DISH_DOES_NOT_FOUND,
                    content =  @Content(schema = @Schema(implementation = ExceptionResponse.class))
            ),
            @ApiResponse(
                    responseCode = RestConstants.SWAGGER_CODE_CONFLICT,
                    description = RestConstants.SWAGGER_ERROR_DISH_DOES_NOT_BELONG_TO_RESTAURANT,
                    content =  @Content(schema = @Schema(implementation = ExceptionResponse.class))
            ),
            @ApiResponse(
                    responseCode = RestConstants.SWAGGER_CODE_CONFLICT,
                    description = RestConstants.SWAGGER_ERROR_USER_ALREADY_HAS_A_PROCESSING_ORDER,
                    content =  @Content(schema = @Schema(implementation = ExceptionResponse.class))
            ),
            @ApiResponse(
                    responseCode = RestConstants.SWAGGER_CODE_BAD_REQUEST,
                    description = RestConstants.SWAGGER_ERROR_VALIDATIONS_DO_NOT_PASS,
                    content =  @Content(schema = @Schema(implementation = ValidationExceptionResponse.class))
            ),
    })
    @PostMapping
    public ResponseEntity<OrderCreatedResponse> createOrder(@RequestBody OrderRequest orderRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                orderHandler.createOrder(orderRequest)
        );
    }

}
