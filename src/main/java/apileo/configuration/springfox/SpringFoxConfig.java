package apileo.configuration.springfox;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.plugins.WebFluxRequestHandlerProvider;
import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;

@EnableOpenApi
@Configuration
public class SpringFoxConfig {

	/**
	 * Documentacao para referencia futura:
	 * https://springfox.github.io/springfox/docs/snapshot/
	 * 
	 * @return
	 */

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2) //
				.apiInfo(getApiInfo()) //
				.enable(true) //
				.select() //
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)) //
				.paths(PathSelectors.any()) //
				.build();
	}

	private ApiInfo getApiInfo() {
		return new ApiInfoBuilder() //
				.title("API-Modelo Leo") //
				.description("Insere, edita, exclui e consulta dados de setores e colaboradores.") //
				.contact(new Contact("Leonardo Duarte", //
						"backEndLeoDuarte.com", //
						"leonardoduarte1305@gmail.com")) //
				.version("Spring 2.6.3") //
				.build();
	}

	@Bean // Este Bean trabalha em conjunto com a prop:
	//spring.mvc.pathmatch.matching-strategy: ant-path-matcher
	public static BeanPostProcessor springfoxHandlerProviderBeanPostProcessor() {
		return new BeanPostProcessor() {

			@Override
			public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
				if (bean instanceof WebMvcRequestHandlerProvider || bean instanceof WebFluxRequestHandlerProvider) {
					customizeSpringfoxHandlerMappings(getHandlerMappings(bean));
				}
				return bean;
			}

			private <T extends RequestMappingInfoHandlerMapping> void customizeSpringfoxHandlerMappings(
					List<T> mappings) {
				List<T> copy = mappings.stream().filter(mapping -> mapping.getPatternParser() == null)
						.collect(Collectors.toList());
				mappings.clear();
				mappings.addAll(copy);
			}

			@SuppressWarnings("unchecked")
			private List<RequestMappingInfoHandlerMapping> getHandlerMappings(Object bean) {
				try {
					Field field = ReflectionUtils.findField(bean.getClass(), "handlerMappings");
					field.setAccessible(true);
					return (List<RequestMappingInfoHandlerMapping>) field.get(bean);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					throw new IllegalStateException(e);
				}
			}
		};
	}

}
