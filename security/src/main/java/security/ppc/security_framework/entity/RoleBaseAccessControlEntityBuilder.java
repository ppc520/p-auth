package security.ppc.security_framework.entity;

import java.util.ArrayList;
import java.util.List;

public class RoleBaseAccessControlEntityBuilder {
    private List<String> roleList = new ArrayList<>();
    private List<String> permissionList = new ArrayList<>();

    public RoleBaseAccessControlEntityBuilder setRoleList(List<String> roleList) {
        this.roleList = roleList;
        return this;
    }

    public RoleBaseAccessControlEntityBuilder setPermissionList(List<String> permissionList) {
        this.permissionList = permissionList;
        return this;
    }

    public RoleBaseAccessControlEntityBuilder setRole(String role) {
        this.roleList.add(role);
        return this;
    }

    public RoleBaseAccessControlEntityBuilder setPermission(String permission) {
        this.permissionList.add(permission);
        return this;
    }

    public RoleBaseAccessControlEntity build(){
        return new RoleBaseAccessControlEntity(this.roleList,this.permissionList);
    }

}
