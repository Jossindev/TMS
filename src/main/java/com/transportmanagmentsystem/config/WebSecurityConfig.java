package com.transportmanagmentsystem.config;

import com.transportmanagmentsystem.controller.ControllerConstants;
import com.transportmanagmentsystem.entity.CustomUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String SLASH_DOUBLE_ASTERISK = "/**";

    @Autowired
    @Qualifier("defaultCustomUserDetailsService")
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                    .disable()
                .authorizeRequests()
                .antMatchers(ControllerConstants.Mapping.BUS_PARK_EMPLOYEES_WORK_CONTROL + ControllerConstants.Mapping.MAIN,
                        ControllerConstants.Mapping.BUS_PARK_EMPLOYEES_WORK_CONTROL + "/login-failure",
                        ControllerConstants.Mapping.BUS_PARK_EMPLOYEES_WORK_CONTROL + "/create-dispatcher",
                        ControllerConstants.Mapping.BUS_PARK_EMPLOYEES_WORK_CONTROL + "/findtrip")
                    .permitAll()
                .antMatchers(ControllerConstants.Mapping.BUS_PARK_EMPLOYEES_WORK_CONTROL + ControllerConstants.Mapping.LOGIN)
                    .not().authenticated()
                .antMatchers(
                        ControllerConstants.Mapping.BUS_PARK_EMPLOYEES_WORK_CONTROL + ControllerConstants.Mapping.DRIVER_INFO_PERFORMING + SLASH_DOUBLE_ASTERISK,
                        ControllerConstants.Mapping.BUS_PARK_EMPLOYEES_WORK_CONTROL + ControllerConstants.Mapping.MEDICAL_EXAMINATION_INFO_PERFORMING + SLASH_DOUBLE_ASTERISK,
                        ControllerConstants.Mapping.BUS_PARK_EMPLOYEES_WORK_CONTROL + ControllerConstants.Mapping.BUS_INFO_PERFORMING + SLASH_DOUBLE_ASTERISK,
                        ControllerConstants.Mapping.BUS_PARK_EMPLOYEES_WORK_CONTROL + ControllerConstants.Mapping.BUS_SERVICE_LOG_PERFORMING + SLASH_DOUBLE_ASTERISK,
                        ControllerConstants.Mapping.BUS_PARK_EMPLOYEES_WORK_CONTROL + ControllerConstants.Mapping.ROUTE_INFO_PERFORMING + SLASH_DOUBLE_ASTERISK,
                        ControllerConstants.Mapping.BUS_PARK_EMPLOYEES_WORK_CONTROL + ControllerConstants.Mapping.SCHEDULE_INFO_PERFORMING + SLASH_DOUBLE_ASTERISK,
                        ControllerConstants.Mapping.BUS_PARK_EMPLOYEES_WORK_CONTROL + ControllerConstants.Mapping.DRIVER_INFO_PERFORMING,
                        ControllerConstants.Mapping.BUS_PARK_EMPLOYEES_WORK_CONTROL + ControllerConstants.Mapping.MEDICAL_EXAMINATION_INFO_PERFORMING,
                        ControllerConstants.Mapping.BUS_PARK_EMPLOYEES_WORK_CONTROL + ControllerConstants.Mapping.BUS_INFO_PERFORMING,
                        ControllerConstants.Mapping.BUS_PARK_EMPLOYEES_WORK_CONTROL + ControllerConstants.Mapping.BUS_SERVICE_LOG_PERFORMING,
                        ControllerConstants.Mapping.BUS_PARK_EMPLOYEES_WORK_CONTROL + ControllerConstants.Mapping.ROUTE_INFO_PERFORMING,
                        ControllerConstants.Mapping.BUS_PARK_EMPLOYEES_WORK_CONTROL + ControllerConstants.Mapping.SCHEDULE_INFO_PERFORMING,
                        ControllerConstants.Mapping.BUS_PARK_EMPLOYEES_WORK_CONTROL + ControllerConstants.Mapping.TRIP_INFO_PREFORMING,
                        ControllerConstants.Mapping.BUS_PARK_EMPLOYEES_WORK_CONTROL + "/fuel-amount-cost-by-route")
                    .hasAnyAuthority(CustomUser.CustomUserRole.DISPATCHER.name())
                .antMatchers(ControllerConstants.Mapping.BUS_PARK_EMPLOYEES_WORK_CONTROL + ControllerConstants.Mapping.DRIVER_ASSIGNED_TRIP_LIST + SLASH_DOUBLE_ASTERISK,
                        ControllerConstants.Mapping.BUS_PARK_EMPLOYEES_WORK_CONTROL + ControllerConstants.Mapping.DRIVER_ACCEPTED_TRIP_LIST)
                    .hasAnyAuthority(CustomUser.CustomUserRole.DRIVER.name())
                .antMatchers(ControllerConstants.Mapping.BUS_PARK_EMPLOYEES_WORK_CONTROL + ControllerConstants.Mapping.TRIP_INFO_PREFORMING + SLASH_DOUBLE_ASTERISK)
                    .hasAnyAuthority(CustomUser.CustomUserRole.DRIVER.name(), CustomUser.CustomUserRole.DISPATCHER.name())
                .anyRequest()
                    .authenticated()
                .and()
                .formLogin()
                .loginPage(ControllerConstants.Mapping.BUS_PARK_EMPLOYEES_WORK_CONTROL + ControllerConstants.Mapping.LOGIN)
                    .failureUrl(ControllerConstants.Mapping.BUS_PARK_EMPLOYEES_WORK_CONTROL + "/login-failure")
                    .defaultSuccessUrl(ControllerConstants.Mapping.BUS_PARK_EMPLOYEES_WORK_CONTROL + ControllerConstants.Mapping.MAIN)
                .and()
                .logout()
                    .logoutUrl(ControllerConstants.Mapping.BUS_PARK_EMPLOYEES_WORK_CONTROL + ControllerConstants.Mapping.LOGOUT)
                    .logoutSuccessUrl(ControllerConstants.Mapping.BUS_PARK_EMPLOYEES_WORK_CONTROL + ControllerConstants.Mapping.LOGIN)
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                    .sessionManagement()
                    .maximumSessions(1);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/assets/**", "/css/**", "/js/**");
    }
}
