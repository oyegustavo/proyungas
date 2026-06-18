package ar.org.proyungas.usuarios.infrastructure.input.user.create;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ar.org.proyungas.usuarios.application.create.UserCreateCommand;
import ar.org.proyungas.usuarios.application.create.UserCreateResult;

@Mapper(componentModel = "spring", uses = {RoleCreateRestMapper.class})
public interface UserCreateRestMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "fullname", target = "fullname")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "enabled", target = "enabled")
    @Mapping(source = "roles", target = "roles")
	UserCreateCommand toCommand(UserCreateRequest request);

    UserCreateResponse toResponse(UserCreateResult result);
}
