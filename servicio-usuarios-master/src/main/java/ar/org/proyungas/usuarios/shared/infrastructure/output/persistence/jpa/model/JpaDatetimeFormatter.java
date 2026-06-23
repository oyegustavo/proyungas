package ar.org.proyungas.usuarios.shared.infrastructure.output.persistence.jpa.model;

import ar.org.proyungas.usuarios.shared.utils.DateUtils;

public class JpaDatetimeFormatter implements JpaFormatter {

    @Override
    public Object format(Object value) {
        if (value == null || "null".equalsIgnoreCase(value.toString())) {
            return value;
        }

        return DateUtils.fromString(value.toString());
    }
}
