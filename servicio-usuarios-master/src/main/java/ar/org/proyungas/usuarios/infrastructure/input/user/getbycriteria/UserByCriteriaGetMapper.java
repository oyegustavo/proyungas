package ar.org.proyungas.usuarios.infrastructure.input.user.getbycriteria;

import org.mapstruct.Mapper;

import ar.org.proyungas.usuarios.application.user.getbycriteria.UserByCriteriaFinderResult;
import ar.org.proyungas.usuarios.shared.utils.PageResult;

@Mapper(componentModel = "spring")
public interface UserByCriteriaGetMapper {
    PageResult<UserByCriteriaGetResponse> toResponse(PageResult<UserByCriteriaFinderResult> results);
}
