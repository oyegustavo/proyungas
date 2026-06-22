package ar.org.proyungas.usuarios.infrastructure.input.user.update;

import org.mapstruct.Mapper;

import ar.org.proyungas.usuarios.application.update.RoleUpdateCommand;

@Mapper(componentModel = "spring")
public interface RoleUpdateRestMapper {
    RoleUpdateCommand toCommand(RoleUpdateRequest request);
}
