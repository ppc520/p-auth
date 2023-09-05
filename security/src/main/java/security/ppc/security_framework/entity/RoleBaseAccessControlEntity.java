package security.ppc.security_framework.entity;

import lombok.Getter;

import java.util.List;
@Getter
public class RoleBaseAccessControlEntity implements AccessControlEntity {
    private List<String> roleList;
    private List<String> permissionList;
    public RoleBaseAccessControlEntity(List<String> roleList, List<String> permissionList) {
        this.roleList = roleList;
        this.permissionList = permissionList;
    }
}
