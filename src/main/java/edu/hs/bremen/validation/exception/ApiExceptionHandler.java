package edu.hs.bremen.validation.exception;

import edu.hs.bremen.model.dto.ErrorDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@ControllerAdvice
public class ApiExceptionHandler {

    public static final String VALIDATION_FAILED = "ERROR_VALIDATION_FAILED";
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiExceptionHandler.class.getSimpleName());

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public @ResponseBody ErrorDto handleException(ValidationException e) {
        return new ErrorDto(VALIDATION_FAILED, e.getMessage());
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public @ResponseBody ErrorDto handleException(MethodArgumentNotValidException e) {
        return new ErrorDto(VALIDATION_FAILED, proccessValidationException(e.getBindingResult().getFieldErrors()));
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SQLException.class)
    public @ResponseBody ErrorDto handleException(SQLException e) {
        return new ErrorDto("ERROR_SQL", e.getMessage());
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ProductNotLinkedException.class)
    public @ResponseBody ErrorDto handleException(ProductNotLinkedException e) {
        return new ErrorDto("ERROR_PRODUCT_LINK", e.getMessage());
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ProductNotValidException.class)
    public @ResponseBody ErrorDto handleException(ProductNotValidException e) {
        return new ErrorDto("ERROR_PRODUCT_INVALID", e.getMessage());
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public @ResponseBody ErrorDto handleException(ConstraintViolationException e) {
        return new ErrorDto(VALIDATION_FAILED, proccessValidationException(e.getConstraintViolations()));
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TransactionSystemException.class)
    public @ResponseBody ErrorDto handleException(TransactionSystemException e) {
        Throwable rootCause = e.getRootCause();
        if (rootCause instanceof ConstraintViolationException) {
            return handleException((ConstraintViolationException) rootCause);
        } else {
            LOGGER.warn("Error while committing transaction. Performing rollback. Message: ", e);
            return new ErrorDto("ERROR_TRANSACTION", "Error while committing transaction. Performing rollback");
        }
    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public @ResponseBody ErrorDto handleException(RuntimeException e) {
        LOGGER.warn("Unexpected error occurred.", e);
        return new ErrorDto("ERROR_UNKNOWN", "Unexpected error occurred: Please contact system administrator.");
    }

    private String proccessValidationException(List<FieldError> fieldErrorList) {
        StringBuilder builder = new StringBuilder("Validation Error: ");
        for (FieldError fieldError : fieldErrorList) {
            builder.append("\n")
                    .append(fieldError.getField())
                    .append(" - ")
                    .append(fieldError.getDefaultMessage());
        }
        return builder.toString();
    }

    private String proccessValidationException(Set<ConstraintViolation<?>> constraintViolationSet) {
        StringBuilder builder = new StringBuilder("Validation Error: ");
        for (ConstraintViolation constraintViolation : constraintViolationSet) {
            builder.append("\n")
                    .append(constraintViolation.getPropertyPath())
                    .append(" - ")
                    .append(constraintViolation.getMessage());
        }
        return builder.toString();
    }
}