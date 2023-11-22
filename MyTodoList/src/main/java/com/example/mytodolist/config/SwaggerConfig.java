package com.example.mytodolist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Server;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
@EnableOpenApi  // 이게 스웨거 3.0 이상버전의 최신 Swagger 어노테이션
public class SwaggerConfig {
    @Bean
    public Docket api(){
        //로컬에 해당하는 서버 url 설정. 배포할때 당장 사용 할 이유는 없어보인다.
        Server serverLocal = new Server("local", "http://localhost:8080/", "for local usages", Collections.emptyList(), Collections.emptyList());
        //배포하고 Swagger를 사용 할 때 정확한 경로로 CRUD 요청을 해야 데이터베이스에 정상적으로 데이터가 입력된다.
        Server testServer = new Server("test", "http://hyeongjun.na2ru2.me/", "for testing", Collections.emptyList(), Collections.emptyList());
        return new Docket(DocumentationType.OAS_30)
                .servers(serverLocal, testServer)
                .useDefaultResponseMessages(true)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.mytodolist"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("TodoList Api Documentation")
                .description("나의 투두리스트 Api 를 테스트 해 봅시다.")
                .version("0.1")
                .build();
    }


}
