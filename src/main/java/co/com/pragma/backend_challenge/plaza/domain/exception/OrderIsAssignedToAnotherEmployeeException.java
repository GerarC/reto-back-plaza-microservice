package co.com.pragma.backend_challenge.plaza.domain.exception;

import co.com.pragma.backend_challenge.plaza.domain.util.DomainConstants;

public class OrderIsAssignedToAnotherEmployeeException extends RuntimeException {
    public OrderIsAssignedToAnotherEmployeeException() {
        super(DomainConstants.ALREADY_ASSIGNED_TO_ANOTHER_EMPLOYEE_MESSAGE);
    }
}
