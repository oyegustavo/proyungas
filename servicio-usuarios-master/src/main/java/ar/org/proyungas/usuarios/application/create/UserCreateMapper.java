package ar.org.proyungas.usuarios.application.create;

import org.mapstruct.Mapper;

import ar.org.proyungas.usuarios.domain.models.User;

@Mapper(componentModel = "spring")
public interface UserCreateMapper {
	UserCreateResult toResult(User user);
	User toDomain(UserCreateCommand commmand);
}
