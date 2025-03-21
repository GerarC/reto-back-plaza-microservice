package co.com.pragma.backend_challenge.plaza.infrastructure.input.rest.v1;


import co.com.pragma.backend_challenge.plaza.application.dto.request.EmployeeRequest;
import co.com.pragma.backend_challenge.plaza.application.dto.request.RestaurantRequest;
import co.com.pragma.backend_challenge.plaza.application.dto.request.filter.DishFilterRequest;
import co.com.pragma.backend_challenge.plaza.application.dto.request.pagination.PageQuery;
import co.com.pragma.backend_challenge.plaza.application.dto.request.pagination.PaginationRequest;
import co.com.pragma.backend_challenge.plaza.application.dto.request.pagination.RestaurantPageQuery;
import co.com.pragma.backend_challenge.plaza.application.dto.response.*;
import co.com.pragma.backend_challenge.plaza.application.handler.RestaurantHandler;
import co.com.pragma.backend_challenge.plaza.infrastructure.configuration.advisor.response.ExceptionResponse;
import co.com.pragma.backend_challenge.plaza.infrastructure.configuration.advisor.response.ValidationExceptionResponse;
import co.com.pragma.backend_challenge.plaza.infrastructure.util.constant.RestConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
            ),
            @ApiResponse(
                    responseCode = RestConstants.SWAGGER_CODE_NOT_FOUND,
                    description = RestConstants.SWAGGER_ERROR_USER_DOES_NOT_EXISTS,
                    content =  @Content(schema = @Schema(implementation = ExceptionResponse.class))
            ),
            @ApiResponse(
                    responseCode = RestConstants.SWAGGER_CODE_CONFLICT,
                    description = RestConstants.SWAGGER_ERROR_USER_IS_NOT_OWNER,
                    content =  @Content(schema = @Schema(implementation = ExceptionResponse.class))
            ),
            @ApiResponse(
                    responseCode = RestConstants.SWAGGER_CODE_CONFLICT,
                    description = RestConstants.SWAGGER_ERROR_RESTAURANT_WITH_NIT_ALREADY_EXISTS,
                    content =  @Content(schema = @Schema(implementation = ExceptionResponse.class))
            ),
            @ApiResponse(
                    responseCode = RestConstants.SWAGGER_CODE_BAD_REQUEST,
                    description = RestConstants.SWAGGER_ERROR_VALIDATIONS_DO_NOT_PASS,
                    content =  @Content(schema = @Schema(implementation = ValidationExceptionResponse.class))
            ),
    })
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<RestaurantResponse> createRestaurant(@RequestBody @Valid RestaurantRequest restaurantRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                restaurantHandler.createRestaurant(restaurantRequest)
        );
    }

    @Operation(summary = RestConstants.SWAGGER_SUMMARY_REGISTER_EMPLOYEE)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = RestConstants.SWAGGER_CODE_CREATED,
                    description = RestConstants.SWAGGER_DESCRIPTION_EMPLOYEE_RELATION_REGISTERED,
                    content =  @Content(schema = @Schema(implementation = EmployeeResponse.class))
            ),
            @ApiResponse(
                    responseCode = RestConstants.SWAGGER_CODE_CONFLICT,
                    description = RestConstants.SWAGGER_ERROR_USER_IS_NOT_OWNER,
                    content =  @Content(schema = @Schema(implementation = ExceptionResponse.class))
            ),
            @ApiResponse(
                    responseCode = RestConstants.SWAGGER_CODE_CONFLICT,
                    description = RestConstants.SWAGGER_ERROR_RESTAURANT_WITH_NIT_ALREADY_EXISTS,
                    content =  @Content(schema = @Schema(implementation = ExceptionResponse.class))
            ),
            @ApiResponse(
                    responseCode = RestConstants.SWAGGER_CODE_BAD_REQUEST,
                    description = RestConstants.SWAGGER_ERROR_VALIDATIONS_DO_NOT_PASS,
                    content =  @Content(schema = @Schema(implementation = ValidationExceptionResponse.class))
            ),
    })
    @PreAuthorize("hasAnyRole('OWNER')")
    @PostMapping("/employees")
    public ResponseEntity<EmployeeResponse> registerEmployee(@RequestBody EmployeeRequest employeeRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                restaurantHandler.registerEmployee(employeeRequest)
        );
    }


    @Operation(summary = RestConstants.SWAGGER_SUMMARY_FIND_PAGE_RESTAURANT)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = RestConstants.SWAGGER_CODE_CREATED,
                    description = RestConstants.SWAGGER_DESCRIPTION_FOUND_PAGE_RESTAURANT,
                    content =  @Content(schema = @Schema(implementation = PageResponse.class))
            ),
    })
    @GetMapping
    public ResponseEntity<PageResponse<RestaurantResponse>> findRestaurants(@Nullable RestaurantPageQuery query){
        PaginationRequest pagination = PaginationRequest.build(query);
        return ResponseEntity.ok(
                restaurantHandler.findPage(pagination)
        );
    }

    @Operation(summary = RestConstants.SWAGGER_SUMMARY_FIND_RESTAURANT_DISHES)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = RestConstants.SWAGGER_CODE_CREATED,
                    description = RestConstants.SWAGGER_DESCRIPTION_FOUND_RESTAURANT_DISHES,
                    content =  @Content(schema = @Schema(implementation = PageResponse.class))
            ),
            @ApiResponse(
                    responseCode = RestConstants.SWAGGER_CODE_NOT_FOUND,
                    description = RestConstants.SWAGGER_ERROR_RESTAURANT_DOES_NOT_FOUND,
                    content =  @Content(schema = @Schema(implementation = ExceptionResponse.class))
            ),
    })
    @GetMapping("/{id}/dishes")
    public ResponseEntity<PageResponse<DishResponse>> getRestaurantDishes(
            @PathVariable String id,
            @Nullable PageQuery query,
            @Nullable DishFilterRequest filterRequest){
        PaginationRequest pagination = PaginationRequest.build(query);
        return ResponseEntity.ok(
                restaurantHandler.findDishesOfRestaurant(id, pagination, filterRequest)
        );
    }

    @Operation(summary = RestConstants.SWAGGER_SUMMARY_FIND_OWNER_RESTAURANT)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = RestConstants.SWAGGER_CODE_CREATED,
                    description = RestConstants.SWAGGER_DESCRIPTION_FOUND_OWNER_RESTAURANT,
                    content =  @Content(schema = @Schema(implementation = PageResponse.class))
            ),
            @ApiResponse(
                    responseCode = RestConstants.SWAGGER_CODE_NOT_FOUND,
                    description = RestConstants.SWAGGER_ERROR_RESTAURANT_DOES_NOT_FOUND,
                    content =  @Content(schema = @Schema(implementation = ExceptionResponse.class))
            ),
    })
    @GetMapping("/owner")
    public ResponseEntity<OwnerRestaurantResponse> getRestaurantCurrentUser(){
        return ResponseEntity.ok(
                restaurantHandler.findCurrentUserRestaurant()
        );
    }
}
