package cn.net.bhe.springbootmybatis.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static com.google.common.collect.Lists.newArrayList;

/**
 * @see <a href="https://www.ibm.com/developerworks/cn/java/j-using-swagger-in-a-spring-boot-project/index.html">url</a>
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any()) // or RequestHandlerSelectors.basePackage(basePackage)
                .paths(PathSelectors.any()) // or Predicates.or(PathSelectors.ant("url"), PathSelectors.ant("url"))
                .build()
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .globalResponseMessage(
                        RequestMethod.POST,
                        newArrayList(
                                new ResponseMessageBuilder()
                                        .code(500)
                                        .message("Server error")
                                        .responseModel(new ModelRef("string"))
                                        .build(),
                                new ResponseMessageBuilder()
                                        .code(403)
                                        .message("Resources unavailable")
                                        .build()
                        ));
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Title",
                "Description",
                "Version",
                "TermsOfServiceUrl",
                new Contact(
                        "Name",
                        "Url",
                        "Email"),
                "License",
                "LicenseUrl",
                Collections.emptyList());
    }
}
