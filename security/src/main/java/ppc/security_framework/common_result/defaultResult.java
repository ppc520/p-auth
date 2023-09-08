package ppc.security_framework.common_result;

import lombok.Data;

@Data
public class defaultResult {
    private String code;
    private String message;
    private Object data;
}
