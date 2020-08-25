package com.ag04smarts.sha.config;

import springfox.documentation.service.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .apiInfo(metaData());
    }

    private ApiInfo metaData() {

        Contact contact = new Contact("Ivona Raguž", "https://github.com/ivona13",
                "ivona.raguz13@gmail.com");

        return new ApiInfo(
                "Summer Health App",
                "We have set up a little fancy clinic in Italian alps under The Dolomites. We'we got couple" +
                        " of best doctors in the area, all the newest hardware equipment, now all we are missing is " +
                        "some cool software te help us manage with patients enlistments, therapies, diagnoses etc. This " +
                        "app helps us to deal with all the stuff.",
                "1.0",
                "Terms of Service: ...",
                contact,
                "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>());
    }
}
