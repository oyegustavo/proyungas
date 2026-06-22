package ar.org.proyungas.usuarios.application.update;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserUpdateResult {
	Integer id;
	String username;
	String fullname;
	String email;
	String password;
	Boolean enabled;
	List<RoleUpdateResult> roles;
}
