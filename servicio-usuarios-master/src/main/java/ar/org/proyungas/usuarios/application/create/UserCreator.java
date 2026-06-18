package ar.org.proyungas.usuarios.application.create;

public interface UserCreator {
	UserCreateResult perform(UserCreateCommand command);
}
