package gov.justucuman.oauth.config;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

@Component
public class CustomOAuth2ExceptionTranslator implements WebResponseExceptionTranslator<OAuth2Exception> {

    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
        if (e instanceof InvalidGrantException) {
            OAuth2Exception oAuth2Exception = new OAuth2Exception("Invalid credentials") {
                @Override
                public int getHttpErrorCode() {
                    return 401; // force 401 instead of 400
                }
            };
            return ResponseEntity.status(oAuth2Exception.getHttpErrorCode()).body(oAuth2Exception);
        }
        throw e;
    }
}

