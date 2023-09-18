package ppc.security_framework.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ppc.security_framework.exception.UnAuthorizeException;
import ppc.security_framework.util.GenerateCommonResultUtil;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @Autowired
    private GenerateCommonResultUtil generateCommonResultUtil;
    @ExceptionHandler(UnAuthorizeException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Map unAuthorizeExceptionHandler(UnAuthorizeException e){
        return generateCommonResultUtil.generate("401",e.getMessage(),null);
    }
}
