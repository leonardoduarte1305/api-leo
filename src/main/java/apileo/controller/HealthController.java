package apileo.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;

import io.swagger.annotations.ApiOperation;

//@CrossOrigin("*")
//@Api(tags = "Health Controller")
//@RequestMapping("/")
//@RestController
public class HealthController {

	@ApiOperation(value = "Verifica se o microsserviço está ativo", code = 200)
	@GetMapping
	public String health() {
		return LocalDateTime.now().toString() + //
				"\n Acesse /swagger-ui/index.html, descubra e teste todas as funcionalidades";
	}

	@Profile(value = { "dev", "test" })
	@ApiOperation(value = "Limpa o cache do Redis no perfil = dev", code = 200)
	@GetMapping("/clear")
	@CacheEvict(cacheNames = { "listaColaboradores", "listaSetores" })
	public String clear() {
		return "Caches esvaziados";
	}
	
	public static void main(String[] args) {
		System.out.println("LocalDateTime: " + LocalDateTime.now());
		System.out.println("LocalDate: " + LocalDate.now());
		System.out.println("Date: " + new Date());
		System.out.println("Calendar: " + Calendar.getInstance());
	}

}
