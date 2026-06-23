package ar.org.proyungas.usuarios.application.user.getbycriteria;

import org.mapstruct.Mapper;

import ar.org.proyungas.usuarios.domain.models.User;
import ar.org.proyungas.usuarios.shared.domain.PageDomain;
import ar.org.proyungas.usuarios.shared.utils.PageResult;

@Mapper(componentModel = "spring")
public interface UserByCriteriaFinderMapper {
	
	UserByCriteriaFinderResult toResult(User user);
	
	PageResult<UserByCriteriaFinderResult> toResult(PageDomain<User> domain);

}
