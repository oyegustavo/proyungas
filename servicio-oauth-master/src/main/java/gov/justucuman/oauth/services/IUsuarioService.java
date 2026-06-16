package gov.justucuman.oauth.services;

import gov.justucuman.oauth.dto.UserDto;

public interface IUsuarioService {
	
	public UserDto findByUsername(String username);

}
