package ppc.security_framework.auto_import_params;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
@Getter
@Setter
@NoArgsConstructor
@ConfigurationProperties("security-frame.jwt-config")
public class JwtConfig {
    public Long accessTokenExpire=10000L;
    public Long refreshTokenExpire=20000L;
    public String secret="ppc";
    public String accessTokenName="access-token";
    public String refreshTokenName="refresh-token";

}
