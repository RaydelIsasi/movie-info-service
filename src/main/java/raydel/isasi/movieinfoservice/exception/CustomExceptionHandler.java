package raydel.isasi.movieinfoservice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import raydel.isasi.movieinfoservice.pojo.ErrorResponse;


import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value
            = {HttpClientErrorException.class})
    @ResponseBody
    protected ResponseEntity<Object> handleNotFoundException(
            RuntimeException ex, WebRequest request) {

        return new ResponseEntity<Object>(new ErrorResponse(ex.getLocalizedMessage()),((HttpClientErrorException)ex).getStatusCode() );
    }


    @ExceptionHandler(value
            = {CustomException.class})
    @ResponseBody
    protected ResponseEntity<Object> handleCustomException(
            RuntimeException ex, WebRequest request) {

        return new ResponseEntity<Object>(new ErrorResponse(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
