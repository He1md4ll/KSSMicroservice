package edu.hs.bremen.validation.exception;

import edu.hs.bremen.model.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ValidationException;
import java.sql.SQLException;
import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public @ResponseBody ErrorDto handleException(ValidationException e) {
        return new ErrorDto("ERROR_VALIDATION_FAILED", e.getMessage());
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public @ResponseBody ErrorDto handleException(MethodArgumentNotValidException e) {
        return new ErrorDto("ERROR_VALIDATION_FAILED", proccessValidationException(e.getBindingResult().getFieldErrors()));
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

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public @ResponseBody ErrorDto handleException(RuntimeException e) {
        return new ErrorDto("ERROR_UNKNOWN", "Unexpected error occurred: Please contact system administrator.");
    }
}