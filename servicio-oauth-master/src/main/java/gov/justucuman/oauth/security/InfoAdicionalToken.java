package gov.justucuman.oauth.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import gov.justucuman.oauth.dto.UserDto;
import gov.justucuman.oauth.services.IUsuarioService;

/**
 * The Class InfoAdicionalToken.
 */
@Component
public class InfoAdicionalToken implements TokenEnhancer{
	
	@Autowired
	private IUsuarioService usuarioService;

	/* (non-Javadoc)
	 * this is to append additional information to the token
	 */
	@Override	
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Map<String, Object> info = new HashMap<String, Object>();
		UserDto usuario = usuarioService.findByUsername(authentication.getName());
//		info.put("nombre", usuario.getNombre());
//		info.put("apellido", usuario.getApellido());
//		info.put("correo", usuario.getEmail());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		return accessToken;
	}

}
