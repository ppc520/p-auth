package ppc.security_framework.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ppc.security_framework.auto_import_params.ResultEntityConfig;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Component
public class GenerateCommonResultUtil {
    @Autowired
    private ResultEntityConfig resultEntityConfig;
    private Map result;
    private String dataFieldName;
    private String codeFieldName;
    private String messageFieldName;

    @PostConstruct
    private void init() {
        result=new HashMap();
        dataFieldName = resultEntityConfig.getDataFieldName();
        codeFieldName = resultEntityConfig.getCodeFieldName();
        messageFieldName = resultEntityConfig.getMessageFieldName();
    }

    public Map generate(String code, String msg, Object data) {
        result.put(dataFieldName,data);
        result.put(codeFieldName,code);
        result.put(messageFieldName,msg);
        return result;
    }
}
