package ar.org.proyungas.usuarios.application.create;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateCommand {
	String username;
	String fullname;
	String email;
	String password;
	Boolean enabled;
	List<RoleCreateCommand> roles;
}
