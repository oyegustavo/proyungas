package ar.org.proyungas.usuarios.shared.infrastructure.output.restclient;

public class RoleNotFoundException extends GenericException{

	public RoleNotFoundException(ErrorCode errorCode) {
		super(errorCode);
	}

}
