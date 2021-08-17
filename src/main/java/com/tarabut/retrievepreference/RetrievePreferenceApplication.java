package com.tarabut.retrievepreference;

import com.google.common.base.Predicates;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class RetrievePreferenceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RetrievePreferenceApplication.class, args);
    }

    @Bean
    public Docket buildSwagger() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("Retrieve Preference")
                .forCodeGeneration(true)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/error")))
                .paths(Predicates.not(PathSelectors.regex("/actuator.*")))
                .build()
                .apiInfo(this.getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("Retrieve Preference Service API")
                .contact("abc@gmail.com")
                .description("Services for Marketing Preference.")
                .version("1.0.0").build();
    }

}
