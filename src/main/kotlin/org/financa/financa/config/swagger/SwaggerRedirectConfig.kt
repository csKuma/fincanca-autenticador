package org.financa.financa.config.swagger

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class SwaggerRedirectConfig {
    @Bean
    fun forwardSwagger(): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addViewControllers(registry: ViewControllerRegistry) {
                registry.addRedirectViewController("/swagger-ui.html", "/swagger-ui/index.html")
            }
        }
    }
}