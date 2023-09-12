package ppc.security_framework.context;

import ppc.security_framework.access_control_model.AccessControlEntity;

public class AccessControlEntityContextHolder{
    private static final ThreadLocal<AccessControlEntity> contextHolder=new ThreadLocal<>();

    public static AccessControlEntity getContextHolder() {
        return contextHolder.get();
    }

    static public void setContextHolder(AccessControlEntity accessControlEntity) {
        contextHolder.set(accessControlEntity);
    }

    static public void removeContextHolder() {
        contextHolder.remove();
    }
}
