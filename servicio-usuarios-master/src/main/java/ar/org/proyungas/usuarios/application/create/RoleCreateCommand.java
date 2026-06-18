package ar.org.proyungas.usuarios.application.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleCreateCommand {
	Integer id;
	String role;
}
