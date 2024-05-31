package com.example.sopra_pflanzenverkauf.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
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

        /**
        http.authorizeHttpRequests()
            // alle Requests die ohne Login erreichbar sind
            .requestMatchers("/login", "/register", "/console/**").permitAll()
            // definiere alle URLs die nur für eine bestimmte Rolle zugänglich sind
            // Achtung: Spring Security fügt automatisch das Prefix "ROLE_" für die Überprüfung ein. Daher verwenden wir
            // hier nicht "ROLE_ADMIN", wie bspw. im TestDataLoader angegeben.
            .requestMatchers("/admin/**").hasRole("ADMIN")
            // alle weiteren Requests erfordern Authentifizierung
            .anyRequest().authenticated()
            // füge CSRF token ein, welches evtl. für AJAX-requests benötigt wird
            .and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                //.ignoringAntMatchers("/console/**")
            // Request zum Aufruf der Login-Seite
            .and().formLogin().loginPage("/login").failureUrl("/login?error=true").permitAll()
            .defaultSuccessUrl("/", true)
            .usernameParameter("username")
            .passwordParameter("password")
            // jeder kann sich ausloggen über den simplen /logout request ausloggen
            .and().logout().permitAll()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/login?logout");


        // Deaktiviert header security. Ermöglicht Nutzung der H2 Console.

        /*
        http.headers().frameOptions().sameOrigin().disable();


        ////////////////////////Aus Demo Projekt
        http.csrf().disable();
        http.headers().frameOptions().disable();
        ////////////////////////Aus Demo Projekt
        */

        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/login", "/register", "/resources/**", "/static/**", "/css/**", "/js/**", "/images/**").permitAll()
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
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
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
