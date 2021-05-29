package apileo.controller;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Abstract Controller")
@RequestMapping("/")
@RestController
public class RootController {

	@ApiOperation(value = "Verifica se o microsserviço está ativo", code = 200)
	@GetMapping
	public String health() {
		return LocalDateTime.now().toString()
				+ "\n Acesse api-leo.herokuapp.com/swagger-ui.html, descubra e teste todas as funcionalidades";
	}

}
