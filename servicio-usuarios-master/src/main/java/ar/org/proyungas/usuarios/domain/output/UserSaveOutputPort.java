package ar.org.proyungas.usuarios.domain.output;

import ar.org.proyungas.usuarios.domain.models.User;

public interface UserSaveOutputPort {
	User perform(User user);
}
