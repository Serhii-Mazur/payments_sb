package mono.it.school.payments.config;

import mono.it.school.payments.exception.InvalidEntityException;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidEntityException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public Map<String, String> handleUserServiceException(InvalidEntityException e) {

        Map<String, String> response = new HashMap<>();
        response.put("error", e.getMessage());

        return response;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, String> handlePSQLException(DataIntegrityViolationException e) {

        Map<String, String> response = new HashMap<>();
        String message = NestedExceptionUtils.getMostSpecificCause(e).getMessage();
        response.put("error", message);

        return response;
    }
}
