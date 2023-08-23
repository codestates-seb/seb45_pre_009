package com.gujo.stackoverflow.config;


import com.fasterxml.classmate.TypeResolver;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.List;


/**
 * api 문서 swagger 3.0
 * Swagger 접속 경로 localhost:port/swagger-ui/index.html (2.9.1ver = swagger-ui.html)
  */
@Configuration
@EnableWebMvc  // 2.9.1 @EnableSwagger2
public class SwaggerConfiguration {


    @Bean
    public Docket api() {

        // AlternateTypeRules 설정

        TypeResolver typeResolver = new TypeResolver();

        AlternateTypeRule rule = AlternateTypeRules.newRule(
                typeResolver.resolve(Pageable.class),
                typeResolver.resolve(myPagable.class)
        );


        return new Docket(DocumentationType.OAS_30) // 2.9.1 SWAGGER_2
                .useDefaultResponseMessages(false)
                .alternateTypeRules(rule)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.gujo.stackoverflow")) // 스캔범위
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .securityContexts(Arrays.asList(securityContext())) // 보안
                .securitySchemes(Arrays.asList(apiKey())); // apikey

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("🚨구조 stackoverflow")
                .description("pre-project 팀 : 🚨구조(009) stackoverflow API 문서화.")
                .version("1.0.0")
                .build();
    }

    /**
     * Swagger UI에서 권한을 부여하면 모든 요청에 JWT가 자동으로 포함됩니다.
     * 토큰을 입력하고 Authorize 를 클릭하기만 하면 API에 대한 모든 요청이 HTTP 헤더에 토큰을 자동으로 포함합니다.
     */

    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEveryThing");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));

    }

    /**
     * pageable property 이름이 swagger에 표현되기때문에
     * 그 타입을 변경해서 처리하기위해 페이징정보를 위해 클래스 생성.
     */

    @Data
    @ApiModel(value = "페이지", description = "페이지 기본 정보")
    static class myPagable {

        @ApiModelProperty(value = "페이지번호")
        private Integer page;

        @ApiModelProperty(value = "페이지크기", allowableValues = "range[0, 100]")
        private Integer size;

        @ApiModelProperty(value = "정렬")
        private List<String> sort;
    }


}
