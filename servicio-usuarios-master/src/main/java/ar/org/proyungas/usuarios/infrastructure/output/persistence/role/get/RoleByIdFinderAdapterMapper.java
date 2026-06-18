package ar.org.proyungas.usuarios.infrastructure.output.persistence.role.get;

import org.mapstruct.Mapper;

import ar.org.proyungas.usuarios.domain.models.Role;
import ar.org.proyungas.usuarios.infrastructure.adapter.output.persistence.entity.RoleEntity;

@Mapper(componentModel = "spring")
public interface RoleByIdFinderAdapterMapper {
	Role toDomain(RoleEntity entity);
}
