package ar.org.proyungas.usuarios.infrastructure.input.user.update;

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
public class RoleUpdateRequest {
	Integer id;
}
