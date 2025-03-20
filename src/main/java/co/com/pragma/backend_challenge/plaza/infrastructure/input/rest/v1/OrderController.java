package co.com.pragma.backend_challenge.plaza.infrastructure.input.rest.v1;

import co.com.pragma.backend_challenge.plaza.application.dto.request.OrderPinRequest;
import co.com.pragma.backend_challenge.plaza.application.dto.request.filter.OrderFilterRequest;
import co.com.pragma.backend_challenge.plaza.application.dto.request.order.OrderRequest;
import co.com.pragma.backend_challenge.plaza.application.dto.request.pagination.PageQuery;
import co.com.pragma.backend_challenge.plaza.application.dto.request.pagination.PaginationRequest;
import co.com.pragma.backend_challenge.plaza.application.dto.response.PageResponse;
import co.com.pragma.backend_challenge.plaza.application.dto.response.order.OrderCreatedResponse;
import co.com.pragma.backend_challenge.plaza.application.dto.response.order.OrderResponse;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderHandler orderHandler;

    @Operation(summary = RestConstants.SWAGGER_SUMMARY_CREATE_ORDER)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = RestConstants.SWAGGER_CODE_CREATED,
                    description = RestConstants.SWAGGER_DESCRIPTION_CREATED_ORDER,
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

    @Operation(summary = RestConstants.SWAGGER_SUMMARY_GET_DISHES)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = RestConstants.SWAGGER_CODE_OK,
                    description = RestConstants.SWAGGER_DESCRIPTION_FOUND_ORDERS,
                    content =  @Content(schema = @Schema(implementation = OrderCreatedResponse.class))
            ),
            @ApiResponse(
                    responseCode = RestConstants.SWAGGER_CODE_UNAUTHORIZED,
                    description = RestConstants.SWAGGER_ERROR_USER_DOES_NOT_WORK_AT_RESTAURANT,
                    content =  @Content(schema = @Schema(implementation = ExceptionResponse.class))
            ),
    })
    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    @GetMapping
    public ResponseEntity<PageResponse<OrderResponse>> getOrders(OrderFilterRequest filter, PageQuery query){
        PaginationRequest paginationRequest = PaginationRequest.build(query);
        return ResponseEntity.ok(
                orderHandler.findOrders(filter, paginationRequest)
        );
    }

    @Operation(summary = RestConstants.SWAGGER_SUMMARY_SET_PREPARING_ORDER)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = RestConstants.SWAGGER_CODE_CREATED,
                    description = RestConstants.SWAGGER_DESCRIPTION_SET_PREPARING_ORDER,
                    content =  @Content(schema = @Schema(implementation = OrderCreatedResponse.class))
            ),
            @ApiResponse(
                    responseCode = RestConstants.SWAGGER_CODE_NOT_FOUND,
                    description = RestConstants.SWAGGER_ERROR_ORDER_DOES_NOT_FOUND,
                    content =  @Content(schema = @Schema(implementation = ExceptionResponse.class))
            ),
            @ApiResponse(
                    responseCode = RestConstants.SWAGGER_CODE_CONFLICT,
                    description = RestConstants.SWAGGER_ERROR_ANOTHER_EMPLOYEE_IS_ATTENDING_ORDER,
                    content =  @Content(schema = @Schema(implementation = ExceptionResponse.class))
            ),
            @ApiResponse(
                    responseCode = RestConstants.SWAGGER_CODE_BAD_REQUEST,
                    description = RestConstants.SWAGGER_ERROR_VALIDATIONS_DO_NOT_PASS,
                    content =  @Content(schema = @Schema(implementation = ValidationExceptionResponse.class))
            ),
    })
    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    @PatchMapping("/{id}/preparing")
    public ResponseEntity<OrderResponse> setAssignedEmployee(@PathVariable Long id){
        return ResponseEntity.ok(
                orderHandler.setAssignedEmployee(id)
        );
    }

    @Operation(summary = RestConstants.SWAGGER_SUMMARY_SET_DONE_ORDER)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = RestConstants.SWAGGER_CODE_CREATED,
                    description = RestConstants.SWAGGER_DESCRIPTION_SET_DONE_ORDER,
                    content =  @Content(schema = @Schema(implementation = OrderCreatedResponse.class))
            ),
            @ApiResponse(
                    responseCode = RestConstants.SWAGGER_CODE_NOT_FOUND,
                    description = RestConstants.SWAGGER_ERROR_ORDER_DOES_NOT_FOUND,
                    content =  @Content(schema = @Schema(implementation = ExceptionResponse.class))
            ),
            @ApiResponse(
                    responseCode = RestConstants.SWAGGER_CODE_CONFLICT,
                    description = RestConstants.SWAGGER_ERROR_ANOTHER_EMPLOYEE_IS_ATTENDING_ORDER,
                    content =  @Content(schema = @Schema(implementation = ExceptionResponse.class))
            ),
            @ApiResponse(
                    responseCode = RestConstants.SWAGGER_CODE_BAD_REQUEST,
                    description = RestConstants.SWAGGER_ERROR_VALIDATIONS_DO_NOT_PASS,
                    content =  @Content(schema = @Schema(implementation = ValidationExceptionResponse.class))
            ),
    })
    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    @PatchMapping("/{id}/done")
    public ResponseEntity<OrderResponse> setOrderAsDone(@PathVariable Long id){
        return ResponseEntity.ok(
                orderHandler.setOrderAsDone(id)
        );
    }

    @Operation(summary = RestConstants.SWAGGER_SUMMARY_SET_DELIVERED_ORDER)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = RestConstants.SWAGGER_CODE_CREATED,
                    description = RestConstants.SWAGGER_DESCRIPTION_SET_DELIVERED_ORDER,
                    content =  @Content(schema = @Schema(implementation = OrderCreatedResponse.class))
            ),
            @ApiResponse(
                    responseCode = RestConstants.SWAGGER_CODE_NOT_FOUND,
                    description = RestConstants.SWAGGER_ERROR_ORDER_DOES_NOT_FOUND,
                    content =  @Content(schema = @Schema(implementation = ExceptionResponse.class))
            ),
            @ApiResponse(
                    responseCode = RestConstants.SWAGGER_CODE_CONFLICT,
                    description = RestConstants.SWAGGER_ERROR_ANOTHER_EMPLOYEE_IS_ATTENDING_ORDER,
                    content =  @Content(schema = @Schema(implementation = ExceptionResponse.class))
            ),
            @ApiResponse(
                    responseCode = RestConstants.SWAGGER_CODE_CONFLICT,
                    description = RestConstants.SWAGGER_ERROR_ORDER_IS_NOT_DONE,
                    content =  @Content(schema = @Schema(implementation = ExceptionResponse.class))
            ),
            @ApiResponse(
                    responseCode = RestConstants.SWAGGER_CODE_BAD_REQUEST,
                    description = RestConstants.SWAGGER_ERROR_VALIDATIONS_DO_NOT_PASS,
                    content =  @Content(schema = @Schema(implementation = ValidationExceptionResponse.class))
            ),
    })
    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    @PatchMapping("/{id}/delivered")
    public ResponseEntity<OrderResponse> setOrderAsReceived(@PathVariable Long id, OrderPinRequest pinRequest){
        return ResponseEntity.ok(
                orderHandler.setOrderAsDelivered(id, pinRequest)
        );
    }

    @Operation(summary = RestConstants.SWAGGER_SUMMARY_SET_CANCELED_ORDER)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = RestConstants.SWAGGER_CODE_CREATED,
                    description = RestConstants.SWAGGER_DESCRIPTION_SET_CANCELED_ORDER,
                    content =  @Content(schema = @Schema(implementation = OrderCreatedResponse.class))
            ),
            @ApiResponse(
                    responseCode = RestConstants.SWAGGER_CODE_NOT_FOUND,
                    description = RestConstants.SWAGGER_ERROR_ORDER_DOES_NOT_FOUND,
                    content =  @Content(schema = @Schema(implementation = ExceptionResponse.class))
            ),
            @ApiResponse(
                    responseCode = RestConstants.SWAGGER_CODE_CONFLICT,
                    description = RestConstants.SWAGGER_ERROR_ORDER_IS_BEING_PREPARED,
                    content =  @Content(schema = @Schema(implementation = ExceptionResponse.class))
            ),
            @ApiResponse(
                    responseCode = RestConstants.SWAGGER_CODE_BAD_REQUEST,
                    description = RestConstants.SWAGGER_ERROR_VALIDATIONS_DO_NOT_PASS,
                    content =  @Content(schema = @Schema(implementation = ValidationExceptionResponse.class))
            ),
    })
    @PreAuthorize("hasAnyRole('CUSTOMER')")
    @PatchMapping("/{id}/canceled")
    public ResponseEntity<OrderResponse> setOrderAsCanceled(@PathVariable Long id){
        return ResponseEntity.ok(
                orderHandler.setOrderAsCanceled(id)
        );
    }
}
