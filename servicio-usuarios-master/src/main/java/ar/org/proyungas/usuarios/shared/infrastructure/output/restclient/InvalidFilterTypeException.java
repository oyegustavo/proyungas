package ar.org.proyungas.usuarios.shared.infrastructure.output.restclient;

public class InvalidFilterTypeException extends GenericException {
    public InvalidFilterTypeException(ErrorCode errorCode) { super(errorCode); }
}

