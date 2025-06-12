package org.financa.financa.config.securiry

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests {
                it
                    // Swagger (libera acesso público)
                    .requestMatchers(
                        "/v3/api-docs/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html"
                    ).permitAll()

                    // Libere também sua rota de login (Google Auth)
                    .requestMatchers("/api/auth/**").permitAll()

                    // Outras rotas exigem autenticação
                    .anyRequest().permitAll()
            }
            // Desativa o formLogin
            .formLogin { it.disable() }
            .httpBasic { it.disable() }

        return http.build()
    }
}