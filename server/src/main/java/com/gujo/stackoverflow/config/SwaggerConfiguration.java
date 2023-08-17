package com.gujo.stackoverflow.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


/**
 * api 문서 swagger 3.0
 * Swagger 접속 경로 localhost:port/swagger-ui/index.html (2.9.1ver = swagger-ui.html)
  */
@Configuration
@EnableWebMvc  // 2.9.1 @EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30) // 2.9.1 SWAGGER_2
                .useDefaultResponseMessages(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.gujo.stackoverflow")) // 스캔범위
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("🚨구조 stackoverflow")
                .description("pre-project 팀 : 🚨구조(009) stackoverflow API 문서화.")
                .version("1.0.0")
                .build();
    }


}
