package com.atomic.config;

import springfox.documentation.service.Contact;
import java.util.ArrayList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.readers.operation.HandlerMethodResolver;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;
import com.fasterxml.classmate.ResolvedType;
import com.fasterxml.classmate.TypeResolver;
import com.fasterxml.classmate.types.ResolvedArrayType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.method.HandlerMethod;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * The Class SwaggerConfig.
 *
 * @author ajuarez
 */
@Configuration
@EnableSwagger2WebFlux
public class SwaggerConfig {
     /** The Constant DEFAULT_CONTACT. */
    public static final Contact DEFAULT_CONTACT = new Contact("A. Juarez", "https://www.pagina.com",
            "correo@mail.com");
    
    /** The Constant DEFAULT_API_INFO. */
    public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("SERVICIO REST WebFlux Api Documentation", 
            "ACADEMY - REST SERVICE WebFlux Api Documentation", "1.0.0",
            "", DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0",
            new ArrayList<>());

    /**
     * Api.
     *
     * @return the docket
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .apiInfo(DEFAULT_API_INFO)
                .select()                
                .apis(RequestHandlerSelectors.basePackage("com.atomic.controller"))               
                .paths(PathSelectors.any())                
                .build();
                
    }
    
    /**
     * Flux method resolver.
     *
     * @param resolver the resolver
     * @return the handler method resolver
     */
    @Bean
    @Primary
    public HandlerMethodResolver fluxMethodResolver(TypeResolver resolver) {
        return new HandlerMethodResolver(resolver) {
            @Override
            public ResolvedType methodReturnType(HandlerMethod handlerMethod) {
                ResolvedType retType = super.methodReturnType(handlerMethod);

                
                while (retType.getErasedType() == Mono.class
                        || retType.getErasedType() == Flux.class
                        || retType.getErasedType() == ResponseEntity.class
                        
                       ) {
                    if (retType.getErasedType() == Flux.class) {

                        ResolvedType type = retType.getTypeBindings().getBoundType(0);
                        retType = new ResolvedArrayType(type.getErasedType(), type.getTypeBindings(), type);
                    } else {
                        retType = retType.getTypeBindings().getBoundType(0);
                    }
                }

                return retType;
            }
        };
    }
}
