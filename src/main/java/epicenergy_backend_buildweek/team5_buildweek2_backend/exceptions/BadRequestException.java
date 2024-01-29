package epicenergy_backend_buildweek.team5_buildweek2_backend.exceptions;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String message) {
        super(message);
    }
}