package ar.org.proyungas.usuarios.shared.infrastructure.output.restclient;


public class InvalidDateRangeException extends GenericException {
    public InvalidDateRangeException(ErrorCode errorCode) {
        super(errorCode);
    }
}
