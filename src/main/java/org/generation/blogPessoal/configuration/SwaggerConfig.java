package org.generation.blogPessoal.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	
	@Bean
	public Docket api() {
		
		/*Define a package onde estão as classes do tipo @RestController, 
		 * para que o Swagger mapeie todas as classes e seus respectivos endpoints
		 *  para montar a documentação do projeto.*/
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis(RequestHandlerSelectors
			.basePackage("org.generation.blogPessoal.controller"))
			.paths(PathSelectors.any())
			.build()
			.apiInfo(metadata())
			.useDefaultResponseMessages(false)
			.globalResponses(HttpMethod.GET, responseMessage())
			.globalResponses(HttpMethod.POST, responseMessage())
			.globalResponses(HttpMethod.PUT, responseMessage())
			.globalResponses(HttpMethod.DELETE, responseMessage());
	}
	
	public static ApiInfo metadata() {
		
		return new ApiInfoBuilder()
			.title("API - Blog Pessoal") // titulo da aplicação que será exibida na documentação
			.description("Projeto API Spring - Blog Pessoal") // descrição da aplicação
			.version("1.0.0") // versão da aplicação
			.license("Apache License Version 2.0") // tipo de licença da aplicação
			.licenseUrl("https://github.com/rafaelq80") // link de acesso da licença
			.contact(contact())
			.build();
	}
	
	private static Contact contact() {

		// Define os dados dos desenvolvedor (nome, website e e-mail) 
		return new Contact("Lucas Bazzani",
		"https://github.com/lucbazzani",
		"lu.bazzani@hotmail.com");
	}
	
	private static List<Response> responseMessage() {
		
		/*Define as mensagens personalizadas para os códigos de Resposta 
		 * do protocolo http (http Response) para todos os verbos (GET, POST, PUT e DELETE).
		 * Cada linha é referente a um Status Code.*/
		return new ArrayList<Response>() {
			
			private static final long serialVersionUID = 1L;
		
			{
				add(new ResponseBuilder().code("200")
				.description("Sucesso!").build());
				add(new ResponseBuilder().code("201")
				.description("Criado!").build());
				add(new ResponseBuilder().code("400")
				.description("Erro na requisição!").build());
				add(new ResponseBuilder().code("401")
				.description("Não Autorizado!").build());
				add(new ResponseBuilder().code("403")
				.description("Proibido!").build());
				add(new ResponseBuilder().code("404")
				.description("Não Encontrado!").build());
				add(new ResponseBuilder().code("500")
				.description("Erro!").build());
			}
		};
	}
}
