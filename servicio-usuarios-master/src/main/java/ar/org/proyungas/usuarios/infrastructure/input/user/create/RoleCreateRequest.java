package ar.org.proyungas.usuarios.infrastructure.input.user.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoleCreateRequest {
	Integer id;
	String role;
}