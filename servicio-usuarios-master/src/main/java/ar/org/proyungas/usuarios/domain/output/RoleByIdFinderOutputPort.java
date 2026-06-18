package ar.org.proyungas.usuarios.domain.output;

import ar.org.proyungas.usuarios.domain.models.Role;

public interface RoleByIdFinderOutputPort {
	Role perform(Integer id);
}
