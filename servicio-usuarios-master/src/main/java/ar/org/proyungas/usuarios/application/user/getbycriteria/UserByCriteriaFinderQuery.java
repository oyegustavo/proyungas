package ar.org.proyungas.usuarios.application.user.getbycriteria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
public class UserByCriteriaFinderQuery {
    private int size;
    private int page;
    private Map<String, String> filters;
}
