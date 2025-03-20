package co.com.pragma.backend_challenge.plaza.infrastructure.configuration.advisor;

import co.com.pragma.backend_challenge.plaza.domain.exception.*;
import co.com.pragma.backend_challenge.plaza.infrastructure.configuration.advisor.response.ExceptionResponse;
import co.com.pragma.backend_challenge.plaza.infrastructure.configuration.advisor.response.ValidationExceptionResponse;
import co.com.pragma.backend_challenge.plaza.infrastructure.util.ExceptionResponseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionAdvisor {
    public static final String NULL_MESSAGE = "null";
    public static final String COLON = ": ";

    @ExceptionHandler(CustomerAlreadyHasAProcessingOrderException.class)
    public ResponseEntity<ExceptionResponse> handleCustomerAlreadyHasAProcessingOrder(CustomerAlreadyHasAProcessingOrderException e){
        return ExceptionResponseBuilder.buildResponse(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DishDoesNotBelongToOrderRestaurantException.class)
    public ResponseEntity<ExceptionResponse> handleDishDoesNotBelongToOrderRestaurant(DishDoesNotBelongToOrderRestaurantException e){
        return ExceptionResponseBuilder.buildResponse(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleEntityAlreadyExists(EntityAlreadyExistsException e){
        return ExceptionResponseBuilder.buildResponse(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleEntityNotFound(EntityNotFoundException e){
        return ExceptionResponseBuilder.buildResponse(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotAuthorizedException.class)
    public ResponseEntity<ExceptionResponse> handleNotAuthorized(NotAuthorizedException e){
        return ExceptionResponseBuilder.buildResponse(e, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NotificationWasNotSentException.class)
    public ResponseEntity<ExceptionResponse> handleNotificationWasNotSent(NotificationWasNotSentException e){
        return ExceptionResponseBuilder.buildResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(OrderDoesNotBelongToTheCustomerException.class)
    public ResponseEntity<ExceptionResponse> handleOrderDoesNotBelongToTheCustomer(OrderDoesNotBelongToTheCustomerException e){
        return ExceptionResponseBuilder.buildResponse(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(OrderIsAssignedToAnotherEmployeeException.class)
    public ResponseEntity<ExceptionResponse> handleOrderIsAlreadyAssigned(OrderIsAssignedToAnotherEmployeeException e){
        return ExceptionResponseBuilder.buildResponse(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(OrderIsBeingPreparedException.class)
    public ResponseEntity<ExceptionResponse> handleOrderIsBeingPrepared(OrderIsBeingPreparedException e){
        return ExceptionResponseBuilder.buildResponse(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(OrderIsNotDoneException.class)
    public ResponseEntity<ExceptionResponse> handleOrderIsNotDone(OrderIsNotDoneException e){
        return ExceptionResponseBuilder.buildResponse(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(OrderIsNotInPreparationStateException.class)
    public ResponseEntity<ExceptionResponse> handleOrderIsNotInPreparationState(OrderIsNotInPreparationStateException e){
        return ExceptionResponseBuilder.buildResponse(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RestaurantDoesNotBelongToUserException.class)
    public ResponseEntity<ExceptionResponse> handleRestaurantDoesNotBelongToUser(RestaurantDoesNotBelongToUserException e){
        return ExceptionResponseBuilder.buildResponse(e, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(SecurityPinDoesNotMatchException.class)
    public ResponseEntity<ExceptionResponse> handleSecurityPinDoesNotMatch(SecurityPinDoesNotMatchException e){
        return ExceptionResponseBuilder.buildResponse(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserRoleMustBeOwnerException.class)
    public ResponseEntity<ExceptionResponse> handleUnderAgedUser(UserRoleMustBeOwnerException e){
        return ExceptionResponseBuilder.buildResponse(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler( MethodArgumentNotValidException.class )
    public ResponseEntity<ValidationExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ValidationExceptionResponse exceptionResponse = ValidationExceptionResponse.builder()
                .statusCode(e.getStatusCode().value())
                .status(HttpStatus.resolve(e.getStatusCode().value()))
                .timestamp(LocalDateTime.now())
                .errors(e.getFieldErrors().stream().map(field -> {
                    StringBuilder sb = new StringBuilder();
                    String rejectedValue = field.getRejectedValue() == null ? NULL_MESSAGE : field.getRejectedValue().toString();
                    sb.append(field.getDefaultMessage()).append(COLON).append(rejectedValue);
                    return sb.toString();
                }).toList())
                .message(e.getBody().getDetail()).build();
        return ResponseEntity.status(exceptionResponse.getStatusCode()).body(exceptionResponse);
    }
}
