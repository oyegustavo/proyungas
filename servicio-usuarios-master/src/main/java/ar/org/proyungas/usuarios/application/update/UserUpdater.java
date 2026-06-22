package ar.org.proyungas.usuarios.application.update;

public interface UserUpdater {
	void perform(UserUpdateCommand command);
}
