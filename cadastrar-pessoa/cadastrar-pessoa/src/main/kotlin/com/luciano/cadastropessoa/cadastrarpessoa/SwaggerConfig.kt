package com.luciano.cadastropessoa.cadastrarpessoa

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfig {
    @Bean
    fun openApi(): Docket = Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.luciano.cadastropessoa.cadastrarpessoa.controller"))
            .paths(PathSelectors.any())
            .build()
            .enable(true)
            .apiInfo(apiInfo())
    private fun apiInfo(): ApiInfo = ApiInfoBuilder()
                .title("API Documentação")
                .description("Documentação da API")
                .version("1.0.0")
                .build()

}
