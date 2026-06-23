package ar.org.proyungas.usuarios.shared.utils;


import java.util.Arrays;
import java.util.List;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import lombok.experimental.UtilityClass;

@UtilityClass
public class JpaFilterUtils {

    private static final String JOIN_SEPARATOR_SPLIT = "\\.";

    public static <T> Expression<T> getExpression(String field, Root<?> root) {
        Path<T> path = null;
        List<String> attributes = Arrays.asList(field.split(JOIN_SEPARATOR_SPLIT));

        for (int i = 0; i < attributes.size(); i++) {
            String attribute = attributes.get(i);

            if (i == attributes.size() - 1) {
                return (path == null) ? root.get(attribute) : path.get(attribute);
            }

            path = (path == null) ? root.join(attribute) : ((Join<?, ?>) path).join(attribute);
        }

        return path;
    }

}
