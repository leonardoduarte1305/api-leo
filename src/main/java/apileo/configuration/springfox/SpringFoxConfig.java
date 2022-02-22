package apileo.configuration.swagger;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import apileo.model.Gestor;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2) //
				.select() //
				.apis(RequestHandlerSelectors //
						.basePackage("apileo")) //
				.build() //
				.ignoredParameterTypes(Gestor.class) // Não mostar Gestor, pois nele há a senha do gestor
				.globalOperationParameters(Arrays.asList( //
						new ParameterBuilder() //
								.name("Authorization") //
								.description("Header para Token JWT") //
								.modelRef(new ModelRef("string")) //
								.parameterType("header") //
								.required(false) //
								.build() //
				)).apiInfo(getApiInfo());
	}

	private ApiInfo getApiInfo() {
		return new ApiInfoBuilder() //
				.title("API Leo") //
				.description("Insere, edita, exclui e consulta dados de setores e colaboradores.") //
				.contact(new Contact("Leonardo Duarte", //
						"backEndLeoDuarte.com", //
						"leonardoduarte1305@gmail.com")) //
				.version("Spring 1.3") //
				.build();
	}

}
