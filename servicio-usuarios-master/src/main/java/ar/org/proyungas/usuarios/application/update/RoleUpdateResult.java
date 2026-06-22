package ar.org.proyungas.usuarios.application.update;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RoleUpdateResult {
	Integer id;
	String role;
}
