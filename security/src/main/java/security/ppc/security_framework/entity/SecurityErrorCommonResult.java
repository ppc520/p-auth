package security.ppc.security_framework.entity;

import lombok.Data;

@Data
public class SecurityErrorCommonResult {
    private int code;
    private String msg;
    SecurityErrorCommonResult(int code, String msg) {
        this.code=code;
        this.msg=msg;
    }
    public static SecurityErrorCommonResult noAuthErr(){
        return new SecurityErrorCommonResult(SecurityErrorCommonResultEnum.NO_AUTH_ERR.getCode(), SecurityErrorCommonResultEnum.NO_AUTH_ERR.getMsg());
    }

    public static SecurityErrorCommonResult wrongToken(){
        return new SecurityErrorCommonResult(SecurityErrorCommonResultEnum.WRONG_TOKEN.getCode(), SecurityErrorCommonResultEnum.WRONG_TOKEN.getMsg());
    }

    public static SecurityErrorCommonResult expiredToken(){
        return new SecurityErrorCommonResult(SecurityErrorCommonResultEnum.EXPIRED_TOKEN.getCode(), SecurityErrorCommonResultEnum.EXPIRED_TOKEN.getMsg());
    }
}
