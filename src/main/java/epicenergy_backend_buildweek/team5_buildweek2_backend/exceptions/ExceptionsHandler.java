package epicenergy_backend_buildweek.team5_buildweek2_backend.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class ExceptionsHandler {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // status 404
    public ErrorsDTO handleNotFound(NotFoundException exception){
        return new ErrorsDTO(exception.getMessage(), LocalDateTime.now());
    }
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // status 400
    public ErrorsDTO handleBadRequest(BadRequestException ex){
        return new ErrorsDTO(ex.getMessage(),LocalDateTime.now());
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // status 500
    public ErrorsDTO handleGenericInternalError(Exception ex){
        ex.printStackTrace();
        return new ErrorsDTO("Errore interno -.-", LocalDateTime.now());
    }
}
