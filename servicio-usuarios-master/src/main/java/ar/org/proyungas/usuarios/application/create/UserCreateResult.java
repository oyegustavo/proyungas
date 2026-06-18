package ar.org.proyungas.usuarios.application.create;

import java.util.List;

import ar.org.proyungas.usuarios.domain.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserCreateResult {
	Integer id;
	String username;
	String fullname;
	String email;
	String password;
	Boolean enabled;
	List<Role> roles;
}
