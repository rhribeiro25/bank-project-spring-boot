package br.com.rhribeiro25.bank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.rhribeiro25.bank.controller"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, responseMessageForGET())
                .globalResponseMessage(RequestMethod.POST, responseMessageForPOST())
                .globalResponseMessage(RequestMethod.PUT, responseMessageForPATCH())
                .globalResponseMessage(RequestMethod.DELETE, responseMessageForDELETE())
                .securitySchemes(Arrays.asList(new BasicAuth("basicAuth")))
                .securityContexts(Arrays.asList(securityContext()))
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Bank Project Spring Boot REST API")
                .description("Um exemplo de aplicação Spring Boot REST API")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .contact(new Contact("Renan Ribeiro", "https://www.linkedin.com/in/rhribeiro25", "rhribeiro_25@hotmail.com"))
                .build();
        }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.ant("/bank/**"))
                .build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("ADMIN", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(
                new SecurityReference("Token Access", authorizationScopes));
    }

    private List<ResponseMessage> responseMessageForGET()
    {
        return new ArrayList<ResponseMessage>() {{
            add(new ResponseMessageBuilder()
                    .code(400)
                    .message("Existe algum erro na requisição!")
                    .responseModel(new ModelRef("Error"))
                    .build());
            add(new ResponseMessageBuilder()
                    .code(401)
                    .message("Sem autorização, efetue login!")
                    .responseModel(new ModelRef("Error"))
                    .build());
            add(new ResponseMessageBuilder()
                    .code(404)
                    .message("Não encontrado!")
                    .responseModel(new ModelRef("Error"))
                    .build());
            add(new ResponseMessageBuilder()
                    .code(500)
                    .message("Ocorreu alguma erro no servidor!")
                    .responseModel(new ModelRef("Error"))
                    .build());
        }};
    }
    private List<ResponseMessage> responseMessageForPOST()
    {
        return new ArrayList<ResponseMessage>() {{
            add(new ResponseMessageBuilder()
                    .code(400)
                    .message("Existe algum erro na requisição!")
                    .responseModel(new ModelRef("Error"))
                    .build());
            add(new ResponseMessageBuilder()
                    .code(401)
                    .message("Sem autorização, efetue login!")
                    .responseModel(new ModelRef("Error"))
                    .build());
            add(new ResponseMessageBuilder()
                    .code(403)
                    .message("Proibido executar essa ação!")
                    .responseModel(new ModelRef("Error"))
                    .build());
            add(new ResponseMessageBuilder()
                    .code(500)
                    .message("Ocorreu alguma erro no servidor!")
                    .responseModel(new ModelRef("Error"))
                    .build());
        }};
    }
    private List<ResponseMessage> responseMessageForPATCH()
    {
        return new ArrayList<ResponseMessage>() {{
            add(new ResponseMessageBuilder()
                    .code(400)
                    .message("Existe algum erro na requisição!")
                    .responseModel(new ModelRef("Error"))
                    .build());
            add(new ResponseMessageBuilder()
                    .code(401)
                    .message("Sem autorização, efetue login!")
                    .responseModel(new ModelRef("Error"))
                    .build());
            add(new ResponseMessageBuilder()
                    .code(403)
                    .message("Proibido executar essa ação!")
                    .responseModel(new ModelRef("Error"))
                    .build());
            add(new ResponseMessageBuilder()
                    .code(404)
                    .message("Não encontrado!")
                    .responseModel(new ModelRef("Error"))
                    .build());
            add(new ResponseMessageBuilder()
                    .code(500)
                    .message("Ocorreu alguma erro no servidor!")
                    .responseModel(new ModelRef("Error"))
                    .build());
        }};
    }

    private List<ResponseMessage> responseMessageForDELETE()
    {
        return new ArrayList<ResponseMessage>() {{
            add(new ResponseMessageBuilder()
                    .code(400)
                    .message("Existe algum erro na requisição!")
                    .responseModel(new ModelRef("Error"))
                    .build());
            add(new ResponseMessageBuilder()
                    .code(401)
                    .message("Sem autorização, efetue login!")
                    .responseModel(new ModelRef("Error"))
                    .build());
            add(new ResponseMessageBuilder()
                    .code(403)
                    .message("Proibido executar essa ação!")
                    .responseModel(new ModelRef("Error"))
                    .build());
            add(new ResponseMessageBuilder()
                    .code(404)
                    .message("Não encontrado!")
                    .responseModel(new ModelRef("Error"))
                    .build());
            add(new ResponseMessageBuilder()
                    .code(500)
                    .message("Ocorreu alguma erro no servidor!")
                    .responseModel(new ModelRef("Error"))
                    .build());
        }};
    }

}
