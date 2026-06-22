package ar.org.proyungas.usuarios.infrastructure.input.user.update;

import java.util.List;

import javax.validation.constraints.NotNull;

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
public class UserUpdateRequest {
	Integer id;
	@NotNull(message = "username must not be null")
	String username;
	@NotNull(message = "fullname must not be null")
	String fullname;
	@NotNull(message = "email must not be null")
	String email;
	String password;
	Boolean enabled;
	List<RoleUpdateRequest> roles;
}
