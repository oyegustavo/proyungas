package ar.org.proyungas.usuarios.shared.infrastructure.output.persistence.jpa.model;


import org.springframework.data.jpa.domain.Specification;

import ar.org.proyungas.usuarios.shared.domain.filter.Filter;

import java.util.Map;

public interface JpaFilter {
    <T> Specification<T> getSpecification();
    JpaFilter fromDomain(Filter domain);
    JpaFilter formatValues(Map<String, JpaFormatter> formatters);
    JpaFilter translateField(Map<String, String> translations);
}
