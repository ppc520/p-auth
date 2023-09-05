package security.ppc.security_framework.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ToString
@Component
@ConfigurationProperties(prefix = "jwt.config")
public class JwtConfig {
    private Long accessTokenExpire;
    private Long refreshTokenExpire;
    private String secret;
}
