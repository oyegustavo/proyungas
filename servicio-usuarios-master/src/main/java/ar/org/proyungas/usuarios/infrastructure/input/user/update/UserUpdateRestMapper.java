package ar.org.proyungas.usuarios.infrastructure.input.user.update;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ar.org.proyungas.usuarios.application.update.UserUpdateCommand;

@Mapper(componentModel = "spring", uses = {RoleUpdateRestMapper.class})
public interface UserUpdateRestMapper {
	@Mapping(source = "id", target = "id")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "fullname", target = "fullname")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "enabled", target = "enabled")
    @Mapping(source = "roles", target = "roles")
	UserUpdateCommand toCommand(UserUpdateRequest request);

}
