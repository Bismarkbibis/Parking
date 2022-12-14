package com.example.Parking.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableSpringConfigured
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {


    //gérer la série de règles d’authentification.
   //la classe AuthenticationManagerBuilder pour gérer la série de règles d’authentification.-> assigner les rôles d’utilisateur et d'administrateur
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("Bismark")
                .password(passwordEncoder()
                        .encode("user123")).roles("USER")
                .and()
                .withUser("Harry").password(passwordEncoder().encode("admin123")).roles("ADMIN","USER");

    }

    //la methode configure pour gerer les filtre
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
/*        http.authorizeHttpRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasRole("ADMIN").
                anyRequest().authenticated()
                .and()
                .formLogin();*/
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }


}
