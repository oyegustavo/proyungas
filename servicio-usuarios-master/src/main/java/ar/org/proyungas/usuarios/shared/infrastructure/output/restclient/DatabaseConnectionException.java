package ar.org.proyungas.usuarios.shared.infrastructure.output.restclient;



public class DatabaseConnectionException extends GenericException {
    public DatabaseConnectionException(ErrorCode errorCode) { super(errorCode); }
}
