package ar.org.proyungas.usuarios.shared.infrastructure.output.restclient;

public class InvalidFilterException extends GenericException {
    public InvalidFilterException(ErrorCode errorCode) { super(errorCode); }
}
