package com.mvolinger.vehiclesapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

  @Bean
  public Docket api() {
      return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
        .paths(PathSelectors.any())
        .build()
        .apiInfo(metaData())
        .enableUrlTemplating(false);
  }

  private ApiInfo metaData() {
      Contact contact = new Contact("mvolinger", "https://github.com/mvolinger", "monise.volinger@gmail.com");

      return new ApiInfoBuilder()
         .title("The Vehicles API")
         .description("REST API to manage Ficticius Clean company vehicles fuel consumption")
         .version("1.0")
         .contact(contact)
         .build();
  }
}
