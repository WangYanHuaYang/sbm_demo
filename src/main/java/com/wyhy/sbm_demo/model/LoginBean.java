package com.wyhy.sbm_demo.model;

import lombok.Data;

import java.util.List;

@Data
public class LoginBean {
    private String id;
    private String userName;
    private String[] roleNames;
    private Integer state;
    private String eMail;
    private String phoneNumber;
    private List<SysPermissionTree> permissionTree;
}
