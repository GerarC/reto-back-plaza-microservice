package co.com.pragma.backend_challenge.plaza.domain.exception;

import co.com.pragma.backend_challenge.plaza.domain.util.DomainConstants;

public class ErrorDuringReportingException extends RuntimeException {


    public ErrorDuringReportingException(){
        super(DomainConstants.ERROR_DURING_THE_PROCESS_OF_REPORT);
    }
}
