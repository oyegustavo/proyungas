package ar.org.proyungas.usuarios.domain.models;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Role {
	Integer id;
	String role;
}
