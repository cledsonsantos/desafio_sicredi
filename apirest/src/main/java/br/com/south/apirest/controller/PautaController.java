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

import br.com.south.apirest.dto.PautaDTO;
import br.com.south.apirest.model.Pauta;
import br.com.south.apirest.service.PautaService;
import br.com.south.apirest.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("pautas")
public class PautaController {
	
	private final DateUtil dateUtil;
	private final PautaService pautaService;
	
	
	@GetMapping
    public ResponseEntity<List<Pauta>> findAllPautas() {
    	log.info("Chamou o findAllPautas - Listar todo as Pautas" + " - " + 
    			dateUtil.formatLocalDateTime(LocalDateTime.now()));
    	
        return ResponseEntity.ok(pautaService.findAll());
    }
	
	@PostMapping("/nova")
	public ResponseEntity<Pauta> nova(@Validated @RequestBody PautaDTO pautaDTO) {
		log.info("Salvando uma pauta: " + " - " + dateUtil.formatLocalDateTime(LocalDateTime.now()));
		
		Pauta pauta = new Pauta();
		BeanUtils.copyProperties(pautaDTO, pauta);
		this.pautaService.salvar(pauta);
		
		return new ResponseEntity<>(pauta, HttpStatus.CREATED);
	}
	

}
