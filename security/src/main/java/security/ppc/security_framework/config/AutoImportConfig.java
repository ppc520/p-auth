package security.ppc.security_framework.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import security.ppc.security_framework.util.JWTUtil;

@Component
@ServletComponentScan
@EnableConfigurationProperties(JwtConfig.class)
@Import(JWTUtil.class)
public class AutoImportConfig {
}
