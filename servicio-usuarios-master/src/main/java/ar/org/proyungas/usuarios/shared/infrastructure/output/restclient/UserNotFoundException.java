package ar.org.proyungas.usuarios.shared.infrastructure.output.restclient;

public class UserNotFoundException extends GenericException{
	public UserNotFoundException(ErrorCode errorCode) {
		super(errorCode);
	}

}
