package ar.org.proyungas.usuarios.shared.infrastructure.output.restclient;

public class MalformedFilterException extends GenericException {
    public MalformedFilterException(ErrorCode errorCode) { super(errorCode); }
}
