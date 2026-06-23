package ar.org.proyungas.usuarios.application.user.getbycriteria;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserByCriteriaFinderResult {
	Integer id;
	String username;
	String fullname;
	String email;
	String password;
	Boolean enabled;
	List<RoleUserByCriteriaFinderResult> roles;
}
