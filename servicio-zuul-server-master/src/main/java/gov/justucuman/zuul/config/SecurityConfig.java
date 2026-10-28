package gov.justucuman.zuul.config;

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http,
                                                            ReactiveJwtAuthenticationConverterAdapter jwtAuthConverter) {
        http
            .csrf(ServerHttpSecurity.CsrfSpec::disable)
            .authorizeExchange(exchanges -> exchanges
                // OAuth token endpoint
                .pathMatchers("/oauth/token").permitAll()

                // servicio-oauth routes
                .pathMatchers("/api/security/**").permitAll()

                // proyungas-gestor routes
                .pathMatchers(HttpMethod.GET, "/api/gestor/**").hasAuthority("ROLE_ADMIN")
                .pathMatchers(HttpMethod.POST, "/api/gestor/**").hasAuthority("ROLE_ADMIN")
                .pathMatchers(HttpMethod.PUT, "/api/gestor/**").hasAuthority("ROLE_ADMIN")
                .pathMatchers(HttpMethod.DELETE, "/api/gestor/**").hasAuthority("ROLE_ADMIN")

                // proyungas-files routes
                .pathMatchers("/api/files/**").hasAuthority("ROLE_ADMIN")

                // everything else requires authentication
                .anyExchange().authenticated()
            )
            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwtSpec -> jwtSpec.jwtAuthenticationConverter(jwtAuthConverter))
            );

        return http.build();
    }

    @Bean
    public ReactiveJwtDecoder jwtDecoder() {
        String secret = "algun_codigo_secreto_aeiou_mas_largo_123456";
        SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        return NimbusReactiveJwtDecoder.withSecretKey(secretKey).build();
    }

    @Bean
    public ReactiveJwtAuthenticationConverterAdapter jwtAuthConverter() {
        JwtAuthenticationConverter delegate = new JwtAuthenticationConverter();
        delegate.setJwtGrantedAuthoritiesConverter(this::extractAuthorities);
        return new ReactiveJwtAuthenticationConverterAdapter(delegate);
    }

    private Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {
        // Your token has "roles": [ { "role": "ROLE_ADMIN", "id": 1 } ]
        List<?> roles = jwt.getClaim("roles");
        if (roles == null) {
            return List.of();
        }
        return roles.stream()
                .map(roleObj -> {
                    if (roleObj instanceof java.util.Map) {
                        Object role = ((java.util.Map<?, ?>) roleObj).get("role");
                        return role != null ? role.toString() : null;
                    }
                    return roleObj.toString();
                })
                .filter(r -> r != null)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
