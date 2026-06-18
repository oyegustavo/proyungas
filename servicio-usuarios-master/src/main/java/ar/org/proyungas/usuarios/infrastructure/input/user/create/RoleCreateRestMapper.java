package ar.org.proyungas.usuarios.infrastructure.input.user.create;

import org.mapstruct.Mapper;

import ar.org.proyungas.usuarios.application.create.RoleCreateCommand;

@Mapper(componentModel = "spring")
public interface RoleCreateRestMapper {
    RoleCreateCommand toCommand(RoleCreateRequest request);
}
