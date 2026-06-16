package gov.justucuman.oauth.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gov.justucuman.oauth.dto.UserDto;

@FeignClient(name="servicio-usuarios")
public interface UsuarioFeignClient {
	
	@GetMapping ("/usuarios/search/buscar-username")
	public UserDto findByUsername(@RequestParam String username);

}
