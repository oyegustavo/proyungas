package ar.org.proyungas.usuarios.shared.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class PageDomain<T>{
    List<T> content;
    Integer number;
    Integer size;
    Integer totalPages;
    Long totalElements;
}
