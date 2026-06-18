package ar.org.proyungas.usuarios.domain.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data 
public class User {
	Integer id;
	String username;
	String fullname;
	String email;
	String password;
	Boolean enabled;
	List<Role> roles;
}
