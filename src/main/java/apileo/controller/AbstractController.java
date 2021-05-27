package apileo.controller;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Abstract Controller")
@RequestMapping("/health")
@RestController
public class AbstractController {

	@ApiOperation(value = "Verifica se o microsserviço está ativo", code = 200)
	@GetMapping
	public String health() {
		return LocalDateTime.now().toString();
	}

}
