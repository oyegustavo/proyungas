package gov.justucuman.oauth.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class RoleDto implements Serializable{

	private static final long serialVersionUID = 4467531611801172710L;
	
	private Integer id;
	private String role;

}
