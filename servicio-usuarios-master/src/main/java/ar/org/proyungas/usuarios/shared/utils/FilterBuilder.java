package ar.org.proyungas.usuarios.shared.utils;


import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ar.org.proyungas.usuarios.shared.domain.filter.Filter;
import ar.org.proyungas.usuarios.shared.infrastructure.output.persistence.jpa.model.ContainsFilter;
import ar.org.proyungas.usuarios.shared.infrastructure.output.persistence.jpa.model.EqualsFilter;
import ar.org.proyungas.usuarios.shared.infrastructure.output.restclient.ErrorCode;
import ar.org.proyungas.usuarios.shared.infrastructure.output.restclient.InvalidFilterTypeException;
import ar.org.proyungas.usuarios.shared.infrastructure.output.restclient.MalformedFilterException;

public class FilterBuilder {
    private static final String KEY_VALUE_SEPARATOR = ":";
    private static final Integer KEY_INDEX = 0;
    private static final String VALUES_SEPARATOR = ",";

    private final Map<String, Filter> filterFactory =
            Collections.unmodifiableMap(Stream.of(
                    new AbstractMap.SimpleEntry<>("equals", new EqualsFilter()),
                    new AbstractMap.SimpleEntry<>("contains", new ContainsFilter())
            ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));


    public List<Filter> build(Map<String, String> mappedFilters) {
        List<Filter> filters = new ArrayList<>();

        for (Map.Entry<String, String> entry : mappedFilters.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            List<String> valueSplit = Arrays.asList(value.split(KEY_VALUE_SEPARATOR));
            Filter filter = buildFilter(key, valueSplit);
            filters.add(filter);
        }

        return filters;
    }

    public Filter buildFilter(String key, List<String> valueSplit) {
        try {
            String filterType = valueSplit.get(KEY_INDEX);
            String value = buildValue(valueSplit);
            List<String> values = Arrays.asList(value.split(VALUES_SEPARATOR));

            validateType(filterType);

            return filterFactory.get(filterType).build(key, values);
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new MalformedFilterException(ErrorCode.MALFORMED_FILTER_ERROR);
        }
    }

    private void validateType(String filterType) {
        if (!filterFactory.containsKey(filterType)) {
            throw new InvalidFilterTypeException(ErrorCode.INVALID_FILTER_TYPE_ERROR);
        }
    }

    private String buildValue(List<String> valueSplit) {
        List<String> effectiveValues = new ArrayList<>(valueSplit);
        effectiveValues.remove(0);

        String values = effectiveValues.stream().map(v -> v.concat(KEY_VALUE_SEPARATOR))
                .collect(Collectors.joining());

        return values.substring(0, values.length() - 1);
    }
}

