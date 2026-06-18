package ar.org.proyungas.usuarios.application.create;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RoleCreateResult {
	Integer id;
	String role;
}
