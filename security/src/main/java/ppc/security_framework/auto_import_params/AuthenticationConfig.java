package ppc.security_framework.auto_import_params;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import ppc.security_framework.auto_import_params.enums.UsernameFieldLocationEnum;

@Getter
@Setter
@NoArgsConstructor
@ConfigurationProperties("security-frame.authentication-config")
public class AuthenticationConfig {
    private String usernameFieldName="username";
    private String loginPath="/login";
    private Boolean pathStrictMatching=false;
    private UsernameFieldLocationEnum usernameFieldLocation= UsernameFieldLocationEnum.requestbody;
}

