package ar.org.proyungas.usuarios;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import ar.org.proyungas.usuarios.models.entity.Role;
import ar.org.proyungas.usuarios.models.entity.User;

/**
 * The Class RepositoryConfig.
 * this optional configuration is added in order to expose Id in JSON
 */
@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer{

	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(User.class,Role.class);
	}

	
}
