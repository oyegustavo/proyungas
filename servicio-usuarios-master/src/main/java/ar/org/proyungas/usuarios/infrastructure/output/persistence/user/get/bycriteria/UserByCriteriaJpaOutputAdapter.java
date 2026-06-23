package ar.org.proyungas.usuarios.infrastructure.output.persistence.user.get.bycriteria;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import ar.org.proyungas.usuarios.domain.models.User;
import ar.org.proyungas.usuarios.domain.output.UserByCriteriaFinderOutputPort;
import ar.org.proyungas.usuarios.infrastructure.adapter.output.persistence.entity.UserEntity;
import ar.org.proyungas.usuarios.infrastructure.output.persistence.repository.UserRepository;
import ar.org.proyungas.usuarios.shared.domain.PageDomain;
import ar.org.proyungas.usuarios.shared.domain.filter.Filter;
import ar.org.proyungas.usuarios.shared.infrastructure.output.persistence.jpa.model.JpaFilter;
import ar.org.proyungas.usuarios.shared.infrastructure.output.persistence.jpa.model.JpaFilterBuilder;
import ar.org.proyungas.usuarios.shared.infrastructure.output.restclient.DatabaseConnectionException;
import ar.org.proyungas.usuarios.shared.infrastructure.output.restclient.ErrorCode;
import ar.org.proyungas.usuarios.shared.infrastructure.output.restclient.InvalidFilterException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserByCriteriaJpaOutputAdapter implements UserByCriteriaFinderOutputPort{
	
    private final UserRepository repository;
    private final UserByCriteriaJpaMapper mapper;

    private static final String DEFAULT_ORDER_COLUMN = "fullname";

	@Override
	public PageDomain<User> perform(Integer page, Integer size, List<Filter> filters) {
        try {
            log.info("Searching users by filters {}, page {} and size {}", filters, page, size);

            List<JpaFilter> jpaFilters = buildJpaFilters(filters);
            Specification<UserEntity> specification = getFiltersSpecification(jpaFilters);
            Page<UserEntity> pageEntity = repository.findAll(
            	    specification,
            	    PageRequest.of(page - 1, size, buildSort())
            	);

            return PageDomain.<User>builder()
                    .content(pageEntity.getContent().stream().map(mapper::toDomain).collect(Collectors.toList()))
                    .number(pageEntity.getNumber())
                    .size(pageEntity.getSize())
                    .totalPages(pageEntity.getTotalPages())
                    .totalElements(pageEntity.getTotalElements())
                    .build();
        } catch (InvalidDataAccessApiUsageException ex) {
            log.error("Error on data type usage on database", ex);
            throw new InvalidFilterException(ErrorCode.INVALID_FILTER);
        } catch (DataAccessException ex) {
            log.error("Error executing query to database", ex);
            throw new DatabaseConnectionException(ErrorCode.DATABASE_ERROR);
        }
	}
	
    private List<JpaFilter> buildJpaFilters(List<Filter> filters) {
        JpaFilterBuilder builder = new JpaFilterBuilder();

        return filters.stream().map(builder::build)
                .collect(Collectors.toList());
    }
	
	
    private Specification<UserEntity> getFiltersSpecification(List<JpaFilter> filters) {
        if (filters.isEmpty()) return null;

        Specification<UserEntity> specification = filters.get(0).getSpecification();
        filters.remove(0);

        for (JpaFilter filter : filters) {
            specification = specification.and(filter.getSpecification());
        }

        return specification;
    }


    private Sort buildSort() {
        return Sort.by(Sort.Direction.ASC, DEFAULT_ORDER_COLUMN);
    }

}
