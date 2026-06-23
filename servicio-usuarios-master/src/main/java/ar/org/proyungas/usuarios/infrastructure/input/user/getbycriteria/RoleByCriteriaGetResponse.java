package ar.org.proyungas.usuarios.infrastructure.input.user.getbycriteria;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RoleByCriteriaGetResponse {
	Integer id;
	String role;
}
