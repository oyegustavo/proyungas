package ar.org.proyungas.usuarios.shared.domain.filter;


import java.util.List;

public interface Filter {
    Filter build(String key, List<String> values);
    String getValue();
    String getKey();
}
