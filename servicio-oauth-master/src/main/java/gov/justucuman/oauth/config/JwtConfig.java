package gov.justucuman.oauth.config;

import java.nio.charset.StandardCharsets;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;

@Configuration
public class JwtConfig {

	@Bean
	public JwtEncoder jwtEncoder() {
	    String secret = "algun_codigo_secreto_aeiou_mas_largo_123456";
	    SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");

	    OctetSequenceKey jwk = new OctetSequenceKey.Builder(secretKey)
	            .algorithm(JWSAlgorithm.HS256)
	            .keyUse(KeyUse.SIGNATURE)
	            .keyID("hs256-key") // any unique string
	            .build();

	    ImmutableJWKSet<com.nimbusds.jose.proc.SecurityContext> jwkSource =
	            new ImmutableJWKSet<>(new JWKSet(jwk));

	    return new NimbusJwtEncoder(jwkSource);
	}


    @Bean
    public JwtDecoder jwtDecoder() {
        String secret = "algun_codigo_secreto_aeiou_mas_largo_123456";
        SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        return NimbusJwtDecoder.withSecretKey(secretKey).build();
    }
}
