package com.wyhy.sbm_demo.Util;

import com.wyhy.sbm_demo.model.SysPermissionTree;

import java.util.ArrayList;
import java.util.List;

public class ServiceUtil {
    /**
     * @Description: 生成权限树
     * @Param: [permissions 权限树]
     * @return: java.util.List<com.genolo.smart_meeting_room.model.SysPermissionTree>
     * @Author: wyhy
     * @Date: 2019/9/12 11:06
     */
    public static List<SysPermissionTree> makePermissionTree(List<SysPermissionTree> permissions){
        List<SysPermissionTree> permissionTree = new ArrayList<SysPermissionTree>();
        for (SysPermissionTree p:permissions){
            if ("0".equals(p.getParentId())) {
                permissionTree.add(getPermissionChild(p,permissions));
            }
        }
        return permissionTree;
    }

    /**
     * @Description: 递归查询子级权限
     * @Param: [permission 权限, permissionsTree 权限列表]
     * @return: com.genolo.smart_meeting_room.model.SysPermissionTree
     * @Author: wyhy
     * @Date: 2019/9/12 11:07
     */
    public static SysPermissionTree getPermissionChild(SysPermissionTree permission,List<SysPermissionTree> permissionsTree){
        for (SysPermissionTree p:permissionsTree){
            if (permission.getId().equals(p.getParentId())){
                if (permission.getChild() == null) {
                    permission.setChild(new ArrayList<SysPermissionTree>());
                }
                permission.getChild().add(getPermissionChild(p,permissionsTree));
            }
        }
        return permission;
    }
}
