package gov.justucuman.oauth.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import gov.justucuman.oauth.clients.UsuarioFeignClient;
import gov.justucuman.oauth.dto.UserDto;

/**
 * The Class UsuarioService.
 * 
 */
@Service
public class UsuarioService implements IUsuarioService, UserDetailsService{
	
	/** The log. */
	private Logger log =  LoggerFactory.getLogger(UsuarioService.class);

	/** The client. */
	@Autowired
	private UsuarioFeignClient client;
	
	/* looks for user consuming an API rest using Feign into servicio-usuarios
	 * 
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserDto usuario = client.findByUsername(username);
		
		if (usuario == null) {
			log.error("Error en el login, no existe el usuario '"+username+"' en el sistema");
			throw new UsernameNotFoundException("Error en el login, no existe el usuario '"+username+"' en el sistema");
		}
		List<GrantedAuthority> authorities = usuario.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getRole()))
				.peek(authority -> log.info("Role: "+ authority.getAuthority()))
				.collect(Collectors.toList());
		
		log.info("Usuario autenticado: "+username);
		
		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(),
				true, true, true, authorities);
		
	}

	@Override
	public UserDto findByUsername(String username) {
		return client.findByUsername(username);
	}

}
