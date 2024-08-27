package by.grsu.liceum.config.security;

import by.grsu.liceum.security.jwt.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableWebSecurity(debug = true)
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class ApiConfiguration {
    private final JwtAuthFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.httpBasic(AbstractHttpConfigurer::disable);
        http.csrf(AbstractHttpConfigurer::disable);
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.authenticationProvider(authenticationProvider).addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        http.authorizeHttpRequests(request -> request.requestMatchers(
                new AntPathRequestMatcher("/swagger-ui/**"),
                new AntPathRequestMatcher("/v3/api-docs/**"),
                new AntPathRequestMatcher("/actuator/**"),
                new AntPathRequestMatcher("/api/v1/auth/**"),
                new AntPathRequestMatcher("/h2-console/**"),
                toH2Console())
            .permitAll());
        //http.headers(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests(request -> request.requestMatchers(new AntPathRequestMatcher("/api/v1/admins/**")).hasAuthority("ROLE_ADMIN"));

        http.authorizeHttpRequests(request -> request.requestMatchers(
                new AntPathRequestMatcher("/api/v1/root/institutes"),
                new AntPathRequestMatcher("/api/v1/root/admins"))
            .hasAuthority("ROLE_SUPER_ADMIN"));

        http.authorizeHttpRequests(request -> request.anyRequest().authenticated());

        return http.build();
    }
}
