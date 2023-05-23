package com.equipoUno.proyectoSalud;

import com.equipoUno.proyectoSalud.servicies.PatientService;
import com.equipoUno.proyectoSalud.servicies.PatientServiceImplement;
import com.equipoUno.proyectoSalud.servicies.UserService;
import com.equipoUno.proyectoSalud.servicies.UserServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Security extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PatientServiceImplement patientServiceImplement;

    @Autowired
    private UserServiceImplement userServiceImplement;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/admin/*", "/api/professional/*").hasRole("ADMIN")
                    .antMatchers("/api/professional/*").hasRole("PROFESSIONAL")
                    .antMatchers("/css/*", "/static/css/js/*", "/img/*", "/**")
                    .permitAll()
                .and().formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/api/auth/login")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/index")
                    .permitAll()
                .and().logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/")
                    .permitAll()
                .and().csrf()
                    .disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userServiceImplement)
                .passwordEncoder(passwordEncoder());
    }


}
