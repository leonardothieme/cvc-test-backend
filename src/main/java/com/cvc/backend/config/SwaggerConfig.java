package com.cvc.backend.config;


import com.cvc.backend.CvcBackendApplication;
import org.bson.types.ObjectId;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage(CvcBackendApplication.class.getPackage().getName()))
                .build().apiInfo(apiInfo())
                .useDefaultResponseMessages(false).genericModelSubstitutes(ResponseEntity.class)
                .directModelSubstitute(ObjectId.class, String.class).tags(new Tag("Hotels", "API"))
                .directModelSubstitute(LocalDate.class, String.class);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("CVC - Hotels").build();
    }


}
