package com.wyhy.sbm_demo.config;

import com.wyhy.sbm_demo.service.securityconfig.MyFilterSecurityInterceptor;
import com.wyhy.sbm_demo.service.securityconfig.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @program: sbm_demo
 * @description: 安全配置
 * @author: WYHY
 * @create: 2018-12-05 12:38
 **/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyFilterSecurityInterceptor myFilterSecurityInterceptor;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService myUserService() { //注册UserDetailsService 的bean
        return new MyUserDetailsService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserService()); //user Details Service验证

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        http
                .formLogin()//自定义登录页面
                .loginPage("/system/login")
                .loginProcessingUrl("/system/login")
                .defaultSuccessUrl("/system/index")
                .and()
                .authorizeRequests()//定义哪些URL需要保护，哪些URL不需要保护
                .antMatchers("/system/login", "swagger-ui.html").permitAll()
                .anyRequest()//任何请求登录后都可以访问
                .authenticated()
                .and()
                .csrf()
                .disable();// 禁用 Spring Security 自带的跨域处理
        http
                .addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class);
    }

}
