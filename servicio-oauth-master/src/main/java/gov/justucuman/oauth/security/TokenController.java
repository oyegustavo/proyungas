package gov.justucuman.oauth.security;

import java.time.Instant;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class TokenController {

    private final JwtEncoder jwtEncoder;
    private final AuthenticationManager authenticationManager;
    private final InfoAdicionalToken infoAdicionalToken;

    public TokenController(JwtEncoder jwtEncoder, AuthenticationManager authenticationManager, InfoAdicionalToken infoAdicionalToken) {
        this.jwtEncoder = jwtEncoder;
        this.authenticationManager = authenticationManager;
        this.infoAdicionalToken = infoAdicionalToken;
    }

    @PostMapping("/oauth/token")
    public ResponseEntity<Map<String, Object>> token(@RequestParam String username,
                                                     @RequestParam String password) {
        Authentication auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, password)
        );

        if (!auth.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                 .body(Collections.singletonMap("error", "Invalid credentials"));
        }

        Instant now = Instant.now();
        Map<String, Object> extraClaims = infoAdicionalToken.additionalClaims(auth.getName());

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("servicio-oauth")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(3600))
                .subject(auth.getName())
                .claim("scope", "read write")
                .claims(c -> c.putAll(extraClaims))
                .build();

        String accessToken = jwtEncoder.encode(
            JwtEncoderParameters.from(
                JwsHeader.with(MacAlgorithm.HS256).build(),
                claims
            )
        ).getTokenValue();

        // Optionally generate a refresh token (simplest: another JWT with longer expiry)
        JwtClaimsSet refreshClaims = JwtClaimsSet.builder()
                .issuer("servicio-oauth")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(86400)) // 24h refresh token
                .subject(auth.getName())
                .claim("scope", "read write")
                .build();

        String refreshToken = jwtEncoder.encode(
            JwtEncoderParameters.from(
                JwsHeader.with(MacAlgorithm.HS256).build(),
                refreshClaims
            )
        ).getTokenValue();

        Map<String, Object> response = new HashMap<>();
        response.put("access_token", accessToken);
        response.put("token_type", "bearer");
        response.put("refresh_token", refreshToken);
        response.put("expires_in", 3600);
        response.put("scope", "read write");
        response.put("jti", UUID.randomUUID().toString());
        
        log.info(response.toString());

        return ResponseEntity.ok(response);
    }

    
}

