package security.ppc.security_framework.entity;

public enum SecurityErrorCommonResultEnum {
    NO_AUTH_ERR(1000,"1000 NO_AUTH_ERR"),
    WRONG_TOKEN(1001,"WRONG_TOKEN"),
    EXPIRED_TOKEN(1002,"EXPIRED_TOKEN"),

    ;
    private int code;
    private String msg;
    SecurityErrorCommonResultEnum(int code, String msg) {
        this.code=code;
        this.msg=msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
