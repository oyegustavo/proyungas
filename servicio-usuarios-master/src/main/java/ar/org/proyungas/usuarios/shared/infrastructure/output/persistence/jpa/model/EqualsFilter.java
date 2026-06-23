package ar.org.proyungas.usuarios.shared.infrastructure.output.persistence.jpa.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;

import ar.org.proyungas.usuarios.shared.domain.filter.Filter;

@Value
@Builder
@AllArgsConstructor
public class EqualsFilter implements Filter {
    String key;
    String value;

    public EqualsFilter() {
        key = null;
        value = null;
    }

    @Override
    public Filter build(String key, List<String> values) {
        return new EqualsFilterBuilder()
                .key(key)
                .value(values.get(0))
                .build();
    }
}
