package com.library.book_api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI bookApiDoc() {
        return new OpenAPI()
                .info(new Info()
                        .title("Book API")
                        .version("v1.0")
                        .description("교봉문고 도서 시스템 API")
                );
    }
}
