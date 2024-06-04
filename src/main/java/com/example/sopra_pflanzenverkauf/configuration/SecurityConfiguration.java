package com.example.sopra_pflanzenverkauf.configuration;


import com.example.sopra_pflanzenverkauf.entity.Plant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/login", "/register", "/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/console/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        // Erlaubt Zugriff auf die Passwortänderungs-URL
                        .requestMatchers("/changePassword").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .failureUrl("/login?error=true")
                        .permitAll()
                        .defaultSuccessUrl("/", true)
                        .usernameParameter("username")
                        .passwordParameter("password")
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/console/**")//Deaktiviert CSRF-Schutz für die H2-Konsole
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                )
                .headers(headers -> headers.frameOptions().sameOrigin() // Ermöglicht das Einbetten der H2-Konsole in Frames
                );

        // Deaktiviert header security. Ermöglicht Nutzung der H2 Console.
        //http.headers().frameOptions().disable();


        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web
                .ignoring()
                // gewähre immer Zugriff auf Dateien in den folgenden Ordnern
                .requestMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }

    /**
     * Password-encoder Bean der Spring Security. Wird zum Verschlüsseln von Passwörtern benötigt.
     *
     * @return BCryptPasswordEncoder bean.
     */

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
