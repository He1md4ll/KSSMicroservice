package edu.hs.bremen.validation.exxception;

import edu.hs.bremen.model.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ValidationException;

@ControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(code = HttpStatus.EXPECTATION_FAILED)
    @ExceptionHandler(ValidationException.class)
    public @ResponseBody ErrorDto handleAccessDeniedException(ValidationException e) {
        return new ErrorDto("ERROR_ACCESS_DENIED", e.getMessage());
    }
}