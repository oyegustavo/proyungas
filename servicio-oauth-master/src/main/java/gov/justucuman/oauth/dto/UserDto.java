package gov.justucuman.oauth.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class UserDto implements Serializable{

	private static final long serialVersionUID = 4002221912401133094L;

	private Integer id;
	private String username;
	private String password;
	private Boolean enabled;
	private List<RoleDto> roles;
	
}
