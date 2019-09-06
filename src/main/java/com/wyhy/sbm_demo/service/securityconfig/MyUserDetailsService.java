package com.wyhy.sbm_demo.service.securityconfig;

import com.wyhy.sbm_demo.Util.Utils;
import com.wyhy.sbm_demo.dao.ManagerMapper;
import com.wyhy.sbm_demo.dao.PermissionMapper;
import com.wyhy.sbm_demo.model.Manager;
import com.wyhy.sbm_demo.model.Permission;
import com.wyhy.sbm_demo.model.Role;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import sun.security.provider.MD5;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: sbm_demo
 * @description: 用户认证
 * @author: WYHY
 * @create: 2019-04-02 16:59
 **/
@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    ManagerMapper managerMapper;

    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        logger.info("用户的用户名: {}", username);
        // TODO 根据用户名，查找到对应的密码，与权限
        Manager manager = managerMapper.findByUserName(username);
        if (manager == null) {
            throw new UsernameNotFoundException("用户名不存在");
        } else {
            List<Permission> permissions = permissionMapper.findByAdminUserId(manager.getId());
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            for (Permission permission : permissions) {
                if (permission != null && permission.getPermissionName() != null){
                    authorities.add(new SimpleGrantedAuthority(permission.getPermissionName()));
                }
            }
            // 封装用户信息，并返回。参数分别是：用户名，密码，用户权限
            return new User(manager.getManagerName(), passwordEncoder.encode(manager.getPassword()), authorities);
        }
    }
}
