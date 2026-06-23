package ar.org.proyungas.usuarios.shared.infrastructure.output.persistence.jpa.model;


public class JpaNumberFormatter implements JpaFormatter {
    @Override
    public Object format(Object value) {
        if (value == null || "null".equalsIgnoreCase(value.toString())) {
            return value;
        }

        return value.toString().trim();
    }
}
