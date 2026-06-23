package ar.org.proyungas.usuarios.infrastructure.input.user.getbycriteria;

import java.util.List;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserByCriteriaGetResponse {
	Integer id;
	String username;
	String fullname;
	String email;
	String password;
	Boolean enabled;
	List<RoleByCriteriaGetResponse> roles;
}
