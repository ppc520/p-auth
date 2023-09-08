package ppc.security_framework.config;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import ppc.security_framework.util.GenerateCommonResultUtil;
import ppc.security_framework.util.JWTUtil;

@Component
@ConfigurationPropertiesScan("ppc.security_framework.auto_import_params")
@Import({JWTUtil.class,GenerateCommonResultUtil.class})
public class AutoImportConfig {
}
