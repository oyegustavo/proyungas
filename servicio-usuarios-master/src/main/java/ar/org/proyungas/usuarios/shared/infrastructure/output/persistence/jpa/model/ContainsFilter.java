package ar.org.proyungas.usuarios.shared.infrastructure.output.persistence.jpa.model;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;

import ar.org.proyungas.usuarios.shared.domain.filter.Filter;

@Value
@Builder
@AllArgsConstructor
public class ContainsFilter implements Filter {
    String key;
    String value;

    public ContainsFilter() {
        key = null;
        value = null;
    }

    @Override
    public Filter build(String key, List<String> values) {
        return new ContainsFilterBuilder()
                .key(key)
                .value(values == null || values.isEmpty() ? null : values.get(0))
                .build();
    }
}
