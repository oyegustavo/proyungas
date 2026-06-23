package ar.org.proyungas.usuarios.application.user.getbycriteria;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RoleUserByCriteriaFinderResult {
	Integer id;
	String role;
}
