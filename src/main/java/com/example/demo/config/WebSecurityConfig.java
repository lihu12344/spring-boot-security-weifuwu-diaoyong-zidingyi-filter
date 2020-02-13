package com.example.demo.config;

import com.example.demo.filter.CustomAuthenticationSuccessHandler;
import com.example.demo.filter.CustomFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.ExceptionTranslationFilter;

import javax.annotation.Resource;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private CustomFilter customFilter;

    @Bean
    public PasswordEncoder initPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().successHandler(new CustomAuthenticationSuccessHandler())
                .and().authorizeRequests()
                .antMatchers("/hello").hasAuthority("admin")
                .antMatchers("/**").permitAll();

        http.addFilterBefore(customFilter, ExceptionTranslationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("gtlx")
                .password(initPasswordEncoder().encode("123456"))
                .authorities("admin");
    }
}
