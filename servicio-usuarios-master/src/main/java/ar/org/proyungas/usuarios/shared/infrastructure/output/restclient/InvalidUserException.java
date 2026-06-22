package ar.org.proyungas.usuarios.shared.infrastructure.output.restclient;

public class InvalidUserException extends GenericException{
	public InvalidUserException(ErrorCode errorCode) {
		super(errorCode);
	}

}
