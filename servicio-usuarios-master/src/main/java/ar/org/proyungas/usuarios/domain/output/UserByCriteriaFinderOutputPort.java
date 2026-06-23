package ar.org.proyungas.usuarios.domain.output;

import java.util.List;

import ar.org.proyungas.usuarios.domain.models.User;
import ar.org.proyungas.usuarios.shared.domain.PageDomain;
import ar.org.proyungas.usuarios.shared.domain.filter.Filter;

public interface UserByCriteriaFinderOutputPort {
	 PageDomain<User> perform(Integer page, Integer size, List<Filter> filters);
}
