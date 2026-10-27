//package gov.justucuman.oauth.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//    	http
//		.cors().and()
//		.authorizeRequests()
//		.antMatchers("/api/security/oauth/token").permitAll()
//		
//		.antMatchers(HttpMethod.GET, "/api/usuarios/users**").hasAuthority("ROLE_ADMIN")
//		.antMatchers(HttpMethod.PUT, "/api/usuarios/users**").hasAuthority("ROLE_ADMIN")
//		.antMatchers(HttpMethod.POST, "/api/usuarios/users**").hasAuthority("ROLE_ADMIN")
//		.antMatchers(HttpMethod.DELETE, "/api/usuarios/users**").hasAuthority("ROLE_ADMIN")
//		.antMatchers(HttpMethod.POST, "/api/usuarios/users**").hasAuthority("ROLE_ADMIN")
//		
//		.antMatchers(HttpMethod.GET, "/api/gestor/action**").hasAuthority("ROLE_SOLICITANTE")
//		.antMatchers(HttpMethod.PUT, "/api/gestor/action**").hasAuthority("ROLE_ADMIN")
//		.antMatchers(HttpMethod.POST, "/api/gestor/action**").hasAuthority("ROLE_ADMIN")
//		.antMatchers(HttpMethod.DELETE, "/api/gestor/action**").hasAuthority("ROLE_ADMIN")
//		
//		.antMatchers(HttpMethod.GET, "/api/gestor/plan-type**").hasAuthority("ROLE_SOLICITANTE")
//		.antMatchers(HttpMethod.PUT, "/api/gestor/vectorial-layer-status**").hasAuthority("ROLE_ADMIN")
//		
//		.antMatchers(HttpMethod.GET, "/api/files/file-manager**").hasAuthority("ROLE_ADMIN")
//		.antMatchers(HttpMethod.PUT, "/api/files/file-manager**").hasAuthority("ROLE_ADMIN")
//		.antMatchers(HttpMethod.POST, "/api/files/file-manager**").hasAuthority("ROLE_ADMIN")
//		.antMatchers(HttpMethod.DELETE, "/api/files/file-manager**").hasAuthority("ROLE_ADMIN")
//		.antMatchers(HttpMethod.POST, "/api/files/file-manager**").hasAuthority("ROLE_ADMIN")
//		
//		.anyRequest().authenticated()
//            .and()
//            .oauth2ResourceServer()
//            .jwt();
//        return http.build();
//    }
//}
//
