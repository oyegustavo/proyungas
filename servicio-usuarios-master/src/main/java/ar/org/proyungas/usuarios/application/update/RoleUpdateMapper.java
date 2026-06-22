package ar.org.proyungas.usuarios.application.update;

import org.mapstruct.Mapper;

import ar.org.proyungas.usuarios.domain.models.Role;

@Mapper(componentModel = "spring")
public interface RoleUpdateMapper {
    Role toDomain(RoleUpdateCommand command);
    RoleUpdateResult toResult(Role role);
}
