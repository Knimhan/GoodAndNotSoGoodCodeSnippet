package com.test.web.rest.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.test.web.business.error.BadRequestException;
import com.test.web.business.error.ErrorCodes;
import com.test.web.business.error.ErrorMessage;
import com.test.web.business.error.NotFoundException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler
{

    private static final Logger log = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request)
    {
        ErrorMessage errorMessage = new ErrorMessage(ErrorCodes.VALIDATION_ERROR);
        errorMessage.setMessage(ex.getBindingResult().getFieldError().getField() +" " + ex.getBindingResult().getFieldError().getDefaultMessage());
        log.debug("method argument not valid", ex);

        return handleExceptionInternal(ex, errorMessage, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(final TypeMismatchException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request)
    {
        ErrorMessage errorMessage = new ErrorMessage(ErrorCodes.VALIDATION_ERROR);
        errorMessage.setMessage(ex.getMessage() + " " + ex.getValue().toString());
        log.debug("type mismatch", ex);

        return handleExceptionInternal(ex, errorMessage, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request)
    {
        ErrorMessage errorMessage = new ErrorMessage(ErrorCodes.VALIDATION_ERROR);
        errorMessage.setMessage(ex.getMessage() + " " + ex.getClass().getSimpleName());
        log.debug("message not readable", ex);

        return handleExceptionInternal(ex, errorMessage, headers, HttpStatus.BAD_REQUEST, request);
    }

    // 400
    @ExceptionHandler({ BadRequestException.class })
    public ResponseEntity<Object> handleBadRequest(final BadRequestException ex, final WebRequest request)
    {
        log.debug("bad request", ex);

        return handleExceptionInternal(ex, ex.getErrorMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    // 404
    @ExceptionHandler({ NotFoundException.class })
    public ResponseEntity<Object> handleNotFound(final NotFoundException ex, final WebRequest request)
    {
        log.debug("not found", ex);

        return handleExceptionInternal(ex, ex.getErrorMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    // all other exceptions based on RuntimeException
    @ExceptionHandler({ RuntimeException.class })
    public ResponseEntity<Object> handleInternal(final RuntimeException ex, final WebRequest request)
    {
        log.error("handleInternal", ex);

        return handleExceptionInternal(ex, ex.getLocalizedMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

}
