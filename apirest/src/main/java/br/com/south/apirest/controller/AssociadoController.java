package br.com.south.apirest.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.south.apirest.dto.AssociadoDTO;
import br.com.south.apirest.model.Associado;
import br.com.south.apirest.service.AssociadoService;
import br.com.south.apirest.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("associados")
public class AssociadoController {
	
	private final DateUtil dateUtil;
	private final AssociadoService associadoService;
	
	
	@GetMapping
    public ResponseEntity<List<Associado>> findAllAssociados() {
    	log.info("Chamou o findAllPautas - Listar todo os Associados " + " - " + 
    			dateUtil.formatLocalDateTime(LocalDateTime.now()));
    	
        return ResponseEntity.ok(associadoService.findAllAssociados());
    }
	
	@PostMapping("/novo")
	public ResponseEntity<Associado> novo(@Validated @RequestBody AssociadoDTO associadoDTO) {
		log.info("Salvando um Associado: " + " - " + dateUtil.formatLocalDateTime(LocalDateTime.now()));

		Associado associado = new Associado();
		BeanUtils.copyProperties(associadoDTO, associado);
		this.associadoService.salvar(associado);
		
		return new ResponseEntity<>(associado , HttpStatus.CREATED);
	}
	

}
