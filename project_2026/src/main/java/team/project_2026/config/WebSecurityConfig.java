package team.project_2026.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final CustomSecuritySuccessHandler customSecuritySuccessHandler;

    public WebSecurityConfig(CustomSecuritySuccessHandler customSecuritySuccessHandler) {
        this.customSecuritySuccessHandler = customSecuritySuccessHandler;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/signin", "/signup", "/save").permitAll()
                .anyRequest().authenticated()
        );

        http.formLogin(login -> login
                .loginPage("/signin")
                .failureUrl("/signin?error=true")
                .successHandler(customSecuritySuccessHandler)
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
        );

        http.logout(logout -> logout
                .logoutUrl("/signout")
                .logoutSuccessUrl("/")
                .permitAll()
        );

        return http.build();
    }
}
