package apileo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableCaching
@EnableSpringDataWebSupport
@EnableWebMvc // @EnableWebMvc ativado para o SpringFox funcionar no spring 2.6.0 +
@SpringBootApplication
public class ApiLeoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiLeoApplication.class, args);
	}

}
