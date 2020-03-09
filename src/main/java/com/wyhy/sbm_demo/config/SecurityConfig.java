package com.wyhy.sbm_demo.config;

import com.wyhy.sbm_demo.Util.Utils;
import com.wyhy.sbm_demo.controller.handler.CustomAuthenticationFailureHandler;
import com.wyhy.sbm_demo.controller.handler.CustomAuthenticationSuccessHandler;
import com.wyhy.sbm_demo.controller.handler.CustomLogoutSuccessHandler;
import com.wyhy.sbm_demo.service.securityservice.CustomUserDetailsService;
import com.wyhy.sbm_demo.service.session.CustomExpiredSessionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * @program: sbm_demo
 * @description: 安全配置
 * @author: WYHY
 * @create: 2018-12-05 12:38
 **/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    @Autowired
    private CustomLogoutSuccessHandler customLogoutSuccessHandler;

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService)
                .passwordEncoder(new PasswordEncoder() {
                @Override
                public String encode(CharSequence charSequence) {
                    return Utils.MD5(charSequence.toString());
                }

                @Override
                public boolean matches(CharSequence charSequence, String s) {
                    return s.equals(Utils.MD5(charSequence.toString()));
                }
            });
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()//自定义登录页面
                .loginPage("/login/login.html")
                .successHandler(customAuthenticationSuccessHandler).permitAll()
                .failureHandler(customAuthenticationFailureHandler)
                .loginProcessingUrl("/login")//自定义登录路径
                .and()
                .authorizeRequests()//定义哪些URL需要保护，哪些URL不需要保护
                .antMatchers("/login/login.html",
                        "/authentication/require",
                        "/system/login/invalid",
                        "/plugins/**",
                        "/login/**",
                        "/authentication/form").permitAll()
                .anyRequest()//任何请求登录后都可以访问
                .authenticated()
                .and()
                .csrf()
                .disable();// 禁用 Spring Security 自带的跨域处理

        http.rememberMe();//自动登录

        http.logout()
                .logoutUrl("/logout")
                .deleteCookies("JSESSIONID")
                .logoutSuccessHandler(customLogoutSuccessHandler);

        http.sessionManagement()
                .invalidSessionUrl("/system/login/invalid")
                .maximumSessions(1)
                // 当达到最大值时，是否保留已经登录的用户
                .maxSessionsPreventsLogin(false)
                // 当达到最大值时，旧用户被踢出后的操作
                .expiredSessionStrategy(new CustomExpiredSessionStrategy())
                .sessionRegistry(sessionRegistry());
    }

}
