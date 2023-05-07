package com.javarush.jira.login.internal.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class FilterConfig {

    private final JwtAuthorizationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final UserDetailsService userDetailsService;

    @Bean
    @Order(1)
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
//        http.securityMatcher("/api/**").authorizeHttpRequests()
//                .requestMatchers("/api/admin/**").hasRole(Role.ADMIN.name())
//                .requestMatchers(HttpMethod.POST, "/api/users").anonymous()
//                .requestMatchers("/api/**").authenticated()
//                .and().httpBasic()
//                .authenticationEntryPoint(restAuthenticationEntryPoint)
//                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER) // support sessions Cookie for UI ajax
//                .and().csrf().disable();
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/", "/doc", "/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**", "/static/**").permitAll()
                .requestMatchers("/api/auth/login", "/view/login").permitAll()
                .requestMatchers("/view/unauth/**", "/ui/register/**", "/ui/password").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

//    @Bean
//    @Order(2)
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests()
//                .requestMatchers("/view/unauth/**", "/ui/register/**", "/ui/password").anonymous()
//                .requestMatchers("/", "/doc", "/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**", "/static/**").permitAll()
//                .requestMatchers("/ui/admin/**", "/view/admin/**").hasRole(Role.ADMIN.name())
//                .anyRequest().authenticated()
//                .and().formLogin().permitAll()
//                .loginPage("/view/login")
//                .defaultSuccessUrl("/", true)
//                .and().oauth2Login()
//                .loginPage("/view/login")
//                .defaultSuccessUrl("/", true)
//                .tokenEndpoint()
//                .accessTokenResponseClient(accessTokenResponseClient())
//                .and()
//                .userInfoEndpoint()
//                .userService(customOAuth2UserService)
//                .and().and().logout()
//                .logoutUrl("/ui/logout")
//                .logoutSuccessUrl("/")
//                .invalidateHttpSession(true)
//                .clearAuthentication(true)
//                .deleteCookies("JSESSIONID")
//                .and().csrf().disable();
//        return http.build();
//    }
}
