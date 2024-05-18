package com.assignment.rewardPoints.config;

import io.swagger.annotations.Api;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class AppConfig {

    @Autowired
    ApplicationProperties props;

    @Autowired
    private Environment environment;

    @Bean
    public Docket api() {
        Contact contact = new Contact(props.getContactName(), "", props.getContactEmail());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder().title(props.getTitle()).description(props.getDescription())
                        .contact(contact).license("0.0.1").build())
                .select().apis(RequestHandlerSelectors.withClassAnnotation(Api.class)).paths(PathSelectors.any())
                .build().groupName("Swagger");
    }

    }
