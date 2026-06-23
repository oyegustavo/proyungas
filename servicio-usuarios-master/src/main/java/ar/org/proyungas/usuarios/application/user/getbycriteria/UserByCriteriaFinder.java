package ar.org.proyungas.usuarios.application.user.getbycriteria;

import ar.org.proyungas.usuarios.shared.utils.PageResult;

public interface UserByCriteriaFinder {
	   PageResult<UserByCriteriaFinderResult> perform(UserByCriteriaFinderQuery query);
}
