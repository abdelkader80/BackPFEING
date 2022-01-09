package com.tshirt.pds.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.tshirt.pds.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    JwtFilter jwtFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/authenticate");
        web.ignoring().antMatchers("/adduser");
        web.ignoring().antMatchers("/categories/**");
        web.ignoring().antMatchers("/produits/**");
        web.ignoring().antMatchers("/app/listcat/**");
        web.ignoring().antMatchers("/app/photoProduct/**");

        web.ignoring().antMatchers("/app/addcat/**");
        web.ignoring().antMatchers("/app/addproduit/**");
        web.ignoring().antMatchers("/app/photoProduct/**");
        
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	//http.formLogin();
        http.csrf().disable().authorizeRequests()
                .and().exceptionHandling().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/app/listproduit/**").hasAuthority("Administrateur");
        http.authorizeRequests().antMatchers("/app/addproduit/").hasAuthority("Administrateur");
       // http.authorizeRequests().antMatchers("/app/uploadphoto/**").hasAuthority("Administrateur");
        http.authorizeRequests().anyRequest().authenticated();
        http.cors();
        
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
}