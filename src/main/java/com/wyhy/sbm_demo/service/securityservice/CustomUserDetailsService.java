package com.wyhy.sbm_demo.service.securityservice;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wyhy.sbm_demo.Util.Utils;
import com.wyhy.sbm_demo.model.SysRole;
import com.wyhy.sbm_demo.model.SysUser;
import com.wyhy.sbm_demo.service.SysPermissionService;
import com.wyhy.sbm_demo.service.SysRoleService;
import com.wyhy.sbm_demo.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @program: sbm_demo
 * @description: 自定义用户和权限信息注入
 * @author: WYHY
 * @create: 2020-01-16 16:26
 **/
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    SysUserService sysUserService;

    @Autowired
    SysRoleService sysRoleService;

    @Autowired
    SysPermissionService sysPermissionService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        SysUser user=sysUserService.getOne(new QueryWrapper<SysUser>().eq("phone_number",username),true);
        if (user==null){
            throw new UsernameNotFoundException("用户不存在");
        }
        for (String roleId:user.getRoles()){
            SysRole role=sysRoleService.getById(roleId);
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return new User(user.getPhoneNumber(),user.getPassword(),authorities);
    }
}
