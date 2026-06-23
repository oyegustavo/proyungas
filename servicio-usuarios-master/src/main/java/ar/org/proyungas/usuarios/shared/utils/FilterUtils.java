package ar.org.proyungas.usuarios.shared.utils;


import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import ar.org.proyungas.usuarios.shared.infrastructure.output.restclient.ErrorCode;
import ar.org.proyungas.usuarios.shared.infrastructure.output.restclient.InvalidFilterException;

@UtilityClass
public class FilterUtils {
    private static final List<String> NON_FILTERS_KEYS = Arrays.asList("page", "size");

    public static void validateFiltersMap(Map<String, String> filtersMap, List<String> allowedFilters) {
        NON_FILTERS_KEYS.forEach(filtersMap.keySet()::remove);

        filtersMap.forEach((k, v) -> {
            if (!allowedFilters.contains(k)) throw new InvalidFilterException(ErrorCode.INVALID_FILTER);
        });
    }
}