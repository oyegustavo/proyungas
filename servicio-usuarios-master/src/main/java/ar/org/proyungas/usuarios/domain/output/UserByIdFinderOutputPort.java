package ar.org.proyungas.usuarios.domain.output;

import ar.org.proyungas.usuarios.domain.models.User;

public interface UserByIdFinderOutputPort {
	User peform(Integer id);
}
