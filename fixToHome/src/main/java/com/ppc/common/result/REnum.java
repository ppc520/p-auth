package com.ppc.common.result;

public enum REnum {
    SUCCESS(20000,"成功",true),
    ERROR(20001,"失败",false),
    ;
    private Integer Code;
    private String message;
    private Boolean flag;

    REnum(Integer code, String message, Boolean flag) {
        Code = code;
        this.message = message;
        this.flag = flag;
    }

    public Integer getCode() {
        return Code;
    }

    public void setCode(Integer code) {
        Code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
}
