package com.jeong.timedeal.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@OpenAPIDefinition(
    info = Info(
        title = "이커머스 API 명세서",
        description = "아커머스 타임딜 서비스 API 명세서",
        version = "v1"
    )
)
class SwaggerConfig {
    @Bean
    fun openApi(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("이커머스 API")
            .pathsToMatch("/v1/**")
            .build()
    }

}