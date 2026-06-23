package ar.org.proyungas.usuarios.shared.utils;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PageResult<T> {
    List<T> content;
    Integer number;
    Integer size;
    Integer totalPages;
    Long totalElements;
}