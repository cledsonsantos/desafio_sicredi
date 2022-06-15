package br.com.south.apirest.apiConsumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cpf", url = "https://user-info.herokuapp.com/users")
public interface APIConsumerCpf {
	
	@GetMapping(value = "/{cpf}", consumes = "application/json")
	String findByCpf(@PathVariable String cpf);
}
