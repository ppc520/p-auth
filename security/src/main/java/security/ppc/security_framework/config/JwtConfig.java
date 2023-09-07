package security.ppc.security_framework.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
@Getter
@Setter
@Component
@ConfigurationProperties("jwt.config")
@Order(2)
public class JwtConfig {
    public Long accessTokenExpire=10000L;
    public Long refreshTokenExpire=20000L;
    public String secret="ppc";
    public String accessTokenName="access-token";
    public String refreshTokenName="refresh-token";
    public JwtConfig(){

    }

    public JwtConfig(Long accessTokenExpire, Long refreshTokenExpire, String secret, String accessTokenName, String refreshTokenName) {
        this.accessTokenExpire = accessTokenExpire;
        this.refreshTokenExpire = refreshTokenExpire;
        this.secret = secret;
        this.accessTokenName = accessTokenName;
        this.refreshTokenName = refreshTokenName;
    }
}
