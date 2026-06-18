package ar.org.proyungas.usuarios.shared.infrastructure.output.restclient;

public class UserBadRequestException extends GenericException{

	public UserBadRequestException(ErrorCode errorCode) {
		super(errorCode);
	}

}
