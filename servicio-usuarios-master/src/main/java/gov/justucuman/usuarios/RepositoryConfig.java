package gov.justucuman.usuarios;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import gov.justucuman.usuarios.models.entity.Role;
import gov.justucuman.usuarios.models.entity.User;

/**
 * The Class RepositoryConfig.
 * this optional configuration is added in order to expose Id in JSON
 */
@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer{

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(User.class,Role.class);
	}

	
}
