package com.rgarcia.w2m.application.exceptions;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.List;
public class ApiError {
    private int code;
    private HttpStatus status;
    private String message;
    private List<String> errors;
    private int internalCode;
    public ApiError() {
        super();
    }

    public ApiError(final HttpStatus status, final String message, final List<String> errors) {
        super();
        this.code = status.value();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ApiError(final HttpStatus status, final String message, final List<String> errors,Integer internalCode) {
        super();
        this.code = status.value();
        this.status = status;
        this.message = message;
        this.errors = errors;
        this.internalCode = internalCode;
    }
    
    public ApiError(final HttpStatus status, final String message, final String error) {
        super();
        this.code = status.value();
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }

    public ApiError(final HttpStatus status, final String message, final String error,Integer internalCode) {
        super();
        this.code = status.value();
        this.status = status;
        this.message = message;
        this.internalCode = internalCode;
        errors = Arrays.asList(error);
    }
    
    public int getCode() {
        return code;
    }

    //
    public void setCode(int code) {
        this.code = code;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(final HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(final List<String> errors) {
        this.errors = errors;
    }

    public void setError(final String error) {
        errors = Arrays.asList(error);
    } 

    public int getInternalCode() {
        return internalCode;
    }

    public void setInternalCode(int internalCode) {
        this.internalCode = internalCode;
    }
    
    
}
