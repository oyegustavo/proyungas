package ar.org.proyungas.usuarios.shared.infrastructure.output.persistence.jpa.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import ar.org.proyungas.usuarios.shared.domain.filter.Filter;
import ar.org.proyungas.usuarios.shared.utils.JpaFilterUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Map;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JpaEqualsFilter implements JpaFilter {

    private String field;
    private Object value;

    @Override
    public <T> Specification<T> getSpecification() {
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder)
                -> criteriaBuilder.equal(JpaFilterUtils.getExpression(field, root), value);
    }

    @Override
    public JpaFilter fromDomain(Filter domain) {
        EqualsFilter equalsFilter = (EqualsFilter) domain;
        Object typedValue = equalsFilter.getValue();

        // Convert string values to proper types based on field
        if ("enabled".equals(equalsFilter.getKey())) {
            typedValue = Boolean.valueOf(String.valueOf(typedValue));
        }
        // You can add more conversions here for other fields (e.g. Integer, Long, etc.)

        return JpaEqualsFilter.builder()
                .field(equalsFilter.getKey())   // must be the entity property name
                .value(typedValue)
                .build();
    }

    @Override
    public JpaFilter formatValues(Map<String, JpaFormatter> formatters) {
        if (formatters.containsKey(field)) {
            JpaFormatter formatter = formatters.get(field);
            Object formattedValue = formatter.format(value);
            this.setValue(formattedValue);
        }

        return this;
    }

    @Override
    public JpaFilter translateField(Map<String, String> translations) {
        if (translations.containsKey(field)) {
            String translatedField = translations.get(field);
            this.setField(translatedField);
        }

        return this;
    }
}
