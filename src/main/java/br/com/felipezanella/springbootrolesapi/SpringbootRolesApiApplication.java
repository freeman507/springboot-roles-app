package br.com.felipezanella.springbootrolesapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringbootRolesApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRolesApiApplication.class, args);
	}


	@GetMapping("adm")
	public String admEndpoint() {
		return "OK";
	}

	@GetMapping("staff")
	public String staffEndpoint() {
		return "OK";
	}

	@GetMapping("client")
	public String clientEndpoint() {
		return "OK";
	}

}
