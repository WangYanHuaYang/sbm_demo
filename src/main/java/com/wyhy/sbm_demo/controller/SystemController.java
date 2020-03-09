package com.wyhy.sbm_demo.controller;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wyhy.sbm_demo.Util.Msg;
import com.wyhy.sbm_demo.Util.MybatisAutoGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @program: sbm_demo
 * @description: 模板接口
 * @author: WYHY
 * @create: 2018-09-21 14:30
 **/
@Slf4j
@RestController
@RequestMapping(value = "/system")
@Api(tags ={"系统管理接口"})
public class SystemController {

    @Autowired
    MybatisAutoGenerator generator;

    @Autowired
    private SessionRegistry sessionRegistry;

    // 原请求信息的缓存及恢复
    private RequestCache requestCache = new HttpSessionRequestCache();

    // 用于重定向
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @ApiOperation(value = "从表生成接口")
    @RequestMapping(value = "/maketable",method = RequestMethod.GET)
    public Msg maketable(@RequestParam("tablename")String tablename){
        try {
            generator.makeTable(tablename);
            return Msg.SUCCESS();
        } catch (MybatisPlusException e) {
            e.printStackTrace();
            return Msg.FAIL();
        }
    }

    @RequestMapping("/login/invalid")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String invalid() {
        return "Session 已过期，请重新登录";
    }

    @RequestMapping("/login/error")
    public void loginError(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        AuthenticationException exception =
                (AuthenticationException)request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
        try {
            response.getWriter().write(exception.toString());
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/kick")
    public String removeUserSessionByUsername(@RequestParam String username) {
        int count = 0;

        // 获取session中所有的用户信息
        List<Object> users = sessionRegistry.getAllPrincipals();
        for (Object principal : users) {
            if (principal instanceof User) {
                String principalName = ((User)principal).getUsername();
                if (principalName.equals(username)) {
                    // 参数二：是否包含过期的Session
                    List<SessionInformation> sessionsInfo = sessionRegistry.getAllSessions(principal, false);
                    if (null != sessionsInfo && sessionsInfo.size() > 0) {
                        for (SessionInformation sessionInformation : sessionsInfo) {
                            sessionInformation.expireNow();
                            count++;
                        }
                    }
                }
            }
        }
        return "操作成功，清理session共" + count + "个";
    }

    @RequestMapping("/admin")
    @PreAuthorize("hasRole('ROLE_admin')")
    public String printAdmin() {
        return "如果你看见这句话，说明你有ROLE_ADMIN角色";
    }

    @RequestMapping("/user")
    @PreAuthorize("hasRole('ROLE_user')")
    public String printUser() {
        return "如果你看见这句话，说明你有ROLE_USER角色";
    }

}
