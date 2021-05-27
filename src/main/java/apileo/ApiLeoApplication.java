package apileo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
//@EnableCaching
public class ApiLeoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiLeoApplication.class, args);
	}

}
