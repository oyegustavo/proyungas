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

		http.authorizeRequests()
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

//	@Bean
//	public FilterRegistrationBean corsFilter() {
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		CorsConfiguration config = new CorsConfiguration();
//		config.setAllowCredentials(true);
//		config.addAllowedOrigin("*");
//		config.addAllowedHeader("*");
//		config.addAllowedMethod("*");
//		source.registerCorsConfiguration("/**", config);
//		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
//		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
//		return bean;
//	}

}
