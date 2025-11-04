package com.printerscheduler.server.security;

import com.printerscheduler.server.service.CustomUserDetailsService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf()
                .disable()
                .authorizeRequests()
                .anyRequest()
                .permitAll()
                .and()
                .logout(logout -> logout
                        .logoutUrl("/user/logout")
                        .logoutSuccessHandler((request, response, authentication) -> {
                            response.setStatus(HttpServletResponse.SC_OK);
                        })
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                )
                .csrf().disable();


        /*  Work in progress, currently using Spring Security's roles and not custom roles
        http.authorizeHttpRequests(authz -> authz

            // All users are allowed to access the login endpoint
            .requestMatchers(new AntPathRequestMatcher("/user/login", HttpMethod.POST.toString())).permitAll()


            // Both "USER" and "ADMIN" roles can access all GET requests
            .requestMatchers(new OrRequestMatcher(
                    new AntPathRequestMatcher("/**", HttpMethod.GET.toString())
            )).hasAnyRole("USER", "ADMIN")


            // Only users with the "ADMIN" role can access POST requests to "/user/**", "/reservation/**", and "/printer/**" URL patterns
            .requestMatchers(new OrRequestMatcher(
                    new AntPathRequestMatcher("/user/**", HttpMethod.POST.toString()),
                    new AntPathRequestMatcher("/reservation/**", HttpMethod.POST.toString()),
                    new AntPathRequestMatcher("/printer/**", HttpMethod.POST.toString())
            )).hasRole("ADMIN")


            // Only users with the "ADMIN" role can access PUT requests to "/user/**", "/reservation/**", and "/printer/**" URL patterns
            .requestMatchers(new OrRequestMatcher(
                    new AntPathRequestMatcher("/user/**", HttpMethod.PUT.toString()),
                    new AntPathRequestMatcher("/reservation/**", HttpMethod.PUT.toString()),
                    new AntPathRequestMatcher("/printer/**", HttpMethod.PUT.toString())
            )).hasRole("ADMIN")


            // Only users with the "ADMIN" role can access DELETE requests to "/user/**", "/reservation/**", and "/printer/**" URL patterns
            .requestMatchers(new OrRequestMatcher(
                    new AntPathRequestMatcher("/user/**", HttpMethod.DELETE.toString()),
                    new AntPathRequestMatcher("/reservation/**", HttpMethod.DELETE.toString()),
                    new AntPathRequestMatcher("/printer/**", HttpMethod.DELETE.toString())
            )).hasRole("ADMIN")


            // This line ensures that any other request must be authenticated
            .anyRequest().authenticated()

        ).httpBasic()
                .and()
                .logout(logout -> logout
                        .logoutUrl("/user/logout")
                        .logoutSuccessHandler((request, response, authentication) -> {
                            response.setStatus(HttpServletResponse.SC_OK);
                        })
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                )
                .csrf().disable();
         */


        return http.build();
    }
}