package com.example.book.publishinghouse.config;

import com.example.book.publishinghouse.security.JwtAuthenticationEntryPoint;
import com.example.book.publishinghouse.security.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Qualifier("currentUserDetailServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .and()
                .authorizeRequests()

//                .antMatchers(HttpMethod.GET, "/admin", "/user/allUsers").hasAnyAuthority("ADMIN")
//                .antMatchers(HttpMethod.POST, "/admin", "/contact", "/pub_house").hasAnyAuthority("ADMIN")
//                .antMatchers(HttpMethod.PUT, "/admin", "/contact", "/pub_house").hasAnyAuthority("ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/admin", "/contact", "/user", "/pub_house").hasAnyAuthority("ADMIN")
//
//                .antMatchers(HttpMethod.POST,"/author", "/book").hasAnyAuthority("USER", "ADMIN")
//                .antMatchers(HttpMethod.PUT,"/author", "/book", "/user").hasAnyAuthority("USER", "ADMIN")
//                .antMatchers(HttpMethod.DELETE,"/author", "/book").hasAnyAuthority("USER", "ADMIN")
//                .antMatchers(HttpMethod.GET, "/user").hasAnyAuthority("USER", "ADMIN")

                .anyRequest().permitAll();

        http
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

        // disable page caching
        http.headers().cacheControl();
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }


    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationTokenFilter();
    }
}
