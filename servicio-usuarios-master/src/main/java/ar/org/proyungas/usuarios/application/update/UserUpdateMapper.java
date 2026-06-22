package ar.org.proyungas.usuarios.application.update;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ar.org.proyungas.usuarios.domain.models.User;

@Mapper(componentModel = "spring", uses = {RoleUpdateMapper.class})
public interface UserUpdateMapper {
	UserUpdateResult toResult(User user);
	
    @Mapping(target = "id", source = "existing.id")
    @Mapping(target = "username", source = "existing.username")
    @Mapping(target = "enabled", source = "command.enabled")
    @Mapping(target = "fullname", source = "command.fullname")
    @Mapping(target = "email", source = "command.email")
    @Mapping(target = "password", source = "command.password")
    @Mapping(target = "roles", source = "command.roles")
	User toDomain(UserUpdateCommand command, User existing);
}
