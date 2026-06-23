package ar.org.proyungas.usuarios.infrastructure.output.persistence.user.get.bycriteria;

import org.mapstruct.Mapper;

import ar.org.proyungas.usuarios.domain.models.User;
import ar.org.proyungas.usuarios.infrastructure.adapter.output.persistence.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserByCriteriaJpaMapper {
	User toDomain(UserEntity entity);
}
