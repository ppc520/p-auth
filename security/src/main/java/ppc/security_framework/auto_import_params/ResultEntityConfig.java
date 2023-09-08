package ppc.security_framework.auto_import_params;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("security-frame.result-entity")
@Getter
@Setter
@NoArgsConstructor
public class ResultEntityConfig {
    private String path="defaultResult";
    private String codeFieldName="code";
    private String messageFieldName="message";
    private String dataFieldName="data";
}
