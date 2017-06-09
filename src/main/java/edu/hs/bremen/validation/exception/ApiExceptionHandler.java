package edu.hs.bremen.validation.exception;

import edu.hs.bremen.model.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ValidationException;
import java.sql.SQLException;

@ControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public @ResponseBody ErrorDto handleException(ValidationException e) {
        return new ErrorDto("ERROR_VALIDATION_FAILED", e.getMessage());
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SQLException.class)
    public @ResponseBody ErrorDto handleException(SQLException e) {
        return new ErrorDto("ERROR_SQL_ERROR", e.getMessage());
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ProductNotLinkedException.class)
    public @ResponseBody ErrorDto handleException(ProductNotLinkedException e) {
        return new ErrorDto("ERROR_PRODUCT_LINK_ERROR", e.getMessage());
    }
}