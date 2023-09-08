package ppc.security_framework.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ppc.security_framework.auto_import_params.ResultEntityConfig;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;

@Component
public class GenerateCommonResultUtil {
    @Autowired
    private ResultEntityConfig resultEntityConfig;
    private Object result;
    private String dataFieldName;
    private String codeFieldName;
    private String messageFieldName;
    @PostConstruct
    private void init(){
        try {
            result=Class.forName(resultEntityConfig.getPath());
            dataFieldName=resultEntityConfig.getDataFieldName();
            codeFieldName=resultEntityConfig.getCodeFieldName();
            messageFieldName=resultEntityConfig.getMessageFieldName();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public Object generate(String code,String msg,Object data){
        try {
            Field codeField=result.getClass().getDeclaredField(codeFieldName);
            Field msgField=result.getClass().getDeclaredField(messageFieldName);
            Field dataField=result.getClass().getDeclaredField(dataFieldName);

            codeField.setAccessible(true);
            codeField.set(result,code);

            msgField.setAccessible(true);
            msgField.set(result,msg);

            dataField.setAccessible(true);
            dataField.set(result,data);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}
