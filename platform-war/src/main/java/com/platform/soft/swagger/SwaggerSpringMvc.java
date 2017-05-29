package com.platform.soft.swagger;

import org.joda.time.LocalDate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by baixiaobin on 16/8/5.
 */
@Configuration
@EnableWebMvc
@EnableSwagger2
@ComponentScan(basePackages=("com.platform.soft.controller.api"))
public class SwaggerSpringMvc extends WebMvcConfigurerAdapter{

    public static String PROJECT_NAME="platform";

    @Bean
    public Docket swaggerSpringMvcPlugin() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/api/**"))//只有api目录下的会生成文档
                .build()
                .useDefaultResponseMessages(true)
                .directModelSubstitute(LocalDate.class, String.class)
                .genericModelSubstitutes(ResponseEntity.class)
               // .securitySchemes(newArrayList(apiKey()))
                .apiInfo(apiInfo());
    }


    private ApiKey apiKey() {
        return new ApiKey("appkey", "appkey", "123456789");
    }


    private ApiInfo apiInfo() {
        Contact contact = new Contact("小兵", null, "xyhhttwan@163.com");
        ApiInfo apiInfo = new ApiInfo(
                PROJECT_NAME + "API",
                PROJECT_NAME + "后台API文档",
                "V1.0",
                "",
                contact,
                "",
                ""
        );
        return apiInfo;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
