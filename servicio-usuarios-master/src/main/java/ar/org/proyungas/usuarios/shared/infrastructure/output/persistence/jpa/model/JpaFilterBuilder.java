package ar.org.proyungas.usuarios.shared.infrastructure.output.persistence.jpa.model;



import java.util.AbstractMap;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ar.org.proyungas.usuarios.shared.domain.filter.Filter;

public class JpaFilterBuilder {

	private final Map<String, String> translations =
		    Collections.unmodifiableMap(
		        Stream.of(
		            new AbstractMap.SimpleEntry<>("username", "username"),
		            new AbstractMap.SimpleEntry<>("fullname", "fullname"),
		            new AbstractMap.SimpleEntry<>("enabled", "enabled")
		        ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
		    );

    private final Map<String, JpaFormatter> formatters =
    	    Collections.unmodifiableMap(
    	        Stream.of(
    	            new AbstractMap.SimpleEntry<>("username", new JpaStringFormatter()),
    	            new AbstractMap.SimpleEntry<>("fullname", new JpaStringFormatter()),
    	            new AbstractMap.SimpleEntry<>("enabled", new JpaBooleanFormatter())
    	        ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
    	    );

    private final Map<Class<? extends Filter>, JpaFilter> jpaFilterFactory =
    	    Collections.unmodifiableMap(
    	        Stream.of(
    	            new AbstractMap.SimpleEntry<>(EqualsFilter.class, new JpaEqualsFilter()),
    	            new AbstractMap.SimpleEntry<>(ContainsFilter.class, new JpaContainsFilter())
    	        ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
    	    );

    public JpaFilter build(Filter filter) {
        return jpaFilterFactory.get(filter.getClass())
                .fromDomain(filter)
                .formatValues(formatters)
                .translateField(translations);
    }
}