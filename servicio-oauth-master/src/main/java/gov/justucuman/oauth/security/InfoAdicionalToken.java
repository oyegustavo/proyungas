package gov.justucuman.oauth.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import gov.justucuman.oauth.dto.UserDto;
import gov.justucuman.oauth.services.IUsuarioService;

/**
 * The Class InfoAdicionalToken.
 */
@Component
public class InfoAdicionalToken {

    private final IUsuarioService usuarioService;

    public InfoAdicionalToken(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public Map<String, Object> additionalClaims(String username) {
        Map<String, Object> info = new HashMap<>();
        UserDto usuario = usuarioService.findByUsername(username);
        if (usuario != null) {
            info.put("nombre", usuario.getUsername()); // adapt to your fields
            info.put("enabled", usuario.getEnabled());
            info.put("roles", usuario.getRoles());
        }
        return info;
    }
}

