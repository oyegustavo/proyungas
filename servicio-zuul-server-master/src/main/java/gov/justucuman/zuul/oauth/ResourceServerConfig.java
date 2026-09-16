package gov.justucuman.zuul.oauth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * The Class ResourceServerConfig.
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.oauth2.config.annotation.web.configuration.
	 * ResourceServerConfigurerAdapter#configure(org.springframework.security.oauth2
	 * .config.annotation.web.configurers.ResourceServerSecurityConfigurer)
	 */
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.oauth2.config.annotation.web.configuration.
	 * ResourceServerConfigurerAdapter#configure(org.springframework.security.config
	 * .annotation.web.builders.HttpSecurity)
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {

		http
		.cors().and()
		.authorizeRequests()
		.antMatchers("/api/security/oauth/token").permitAll()
		
		.antMatchers(HttpMethod.GET, "/api/usuarios/users**").hasAuthority("ROLE_ADMIN")
		.antMatchers(HttpMethod.PUT, "/api/usuarios/users**").hasAuthority("ROLE_ADMIN")
		.antMatchers(HttpMethod.POST, "/api/usuarios/users**").hasAuthority("ROLE_ADMIN")
		.antMatchers(HttpMethod.DELETE, "/api/usuarios/users**").hasAuthority("ROLE_ADMIN")
		.antMatchers(HttpMethod.POST, "/api/usuarios/users**").hasAuthority("ROLE_ADMIN")
		
		.antMatchers(HttpMethod.GET, "/api/gestor/action**").hasAuthority("ROLE_SOLICITANTE")
		.antMatchers(HttpMethod.PUT, "/api/gestor/action**").hasAuthority("ROLE_ADMIN")
		.antMatchers(HttpMethod.POST, "/api/gestor/action**").hasAuthority("ROLE_ADMIN")
		.antMatchers(HttpMethod.DELETE, "/api/gestor/action**").hasAuthority("ROLE_ADMIN")
		
		.antMatchers(HttpMethod.GET, "/api/gestor/plan-type**").hasAuthority("ROLE_SOLICITANTE")
		.antMatchers(HttpMethod.PUT, "/api/gestor/vectorial-layer-status**").hasAuthority("ROLE_ADMIN")
		
		.antMatchers(HttpMethod.GET, "/api/files/file-manager**").hasAuthority("ROLE_ADMIN")
		.antMatchers(HttpMethod.PUT, "/api/files/file-manager**").hasAuthority("ROLE_ADMIN")
		.antMatchers(HttpMethod.POST, "/api/files/file-manager**").hasAuthority("ROLE_ADMIN")
		.antMatchers(HttpMethod.DELETE, "/api/files/file-manager**").hasAuthority("ROLE_ADMIN")
		.antMatchers(HttpMethod.POST, "/api/files/file-manager**").hasAuthority("ROLE_ADMIN")
		
		.anyRequest().authenticated();
	}

	/**
	 * Token store.
	 *
	 * @return the jwt token store
	 */
	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	/**
	 * Access token converter.
	 *
	 * @return the jwt access token converter
	 */
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
		jwtAccessTokenConverter.setSigningKey("algun_codigo_secreto_aeiou");
		return jwtAccessTokenConverter;
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration configuration = new CorsConfiguration();
	    configuration.setAllowCredentials(true);
	    configuration.addAllowedOrigin("http://localhost:5173"); // React dev server
	    configuration.addAllowedHeader("*");
	    configuration.addAllowedMethod("*");

	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", configuration);
	    return source;
	}

}
