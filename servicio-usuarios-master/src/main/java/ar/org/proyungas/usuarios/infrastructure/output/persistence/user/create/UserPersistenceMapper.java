package ar.org.proyungas.usuarios.infrastructure.output.persistence.user.create;

import org.mapstruct.Mapper;

import ar.org.proyungas.usuarios.domain.models.User;
import ar.org.proyungas.usuarios.infrastructure.adapter.output.persistence.entity.UserEntity;

@Mapper
public interface UserPersistenceMapper {
	User toDomain(UserEntity entity);
	UserEntity toEntity(User user);
}
