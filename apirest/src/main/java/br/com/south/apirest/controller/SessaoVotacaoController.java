package br.com.south.apirest.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.south.apirest.dto.SessaoVotacaoDTO;
import br.com.south.apirest.model.SessaoVotacao;
import br.com.south.apirest.service.SessaoVotacaoService;
import br.com.south.apirest.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@RequiredArgsConstructor
@Log4j2
@RestController
@RequestMapping("sessoes")
public class SessaoVotacaoController {
	
	private final DateUtil dateUtil;
	private final SessaoVotacaoService sessaoVotacaoService;
	
	@GetMapping
    public ResponseEntity<List<SessaoVotacao>> findAllSessoes() {
    	log.info("Listando todas as sessões " + " - " + dateUtil.formatLocalDateTime(LocalDateTime.now()));
    	
        return ResponseEntity.ok(sessaoVotacaoService.findAllSessao());
    }
	
	
	
	@PostMapping("/abrir")
	public ResponseEntity<SessaoVotacao> abrirSessao( @RequestBody SessaoVotacaoDTO sessaoVotacaoDTO) {
	   log.info("Abrindo uma nova Sessão de Votação: " + " - " + dateUtil.formatLocalDateTime(LocalDateTime.now()));
	   
	   SessaoVotacao sessaoVotacao = this.sessaoVotacaoService.abrirSessaoParaVotacao(sessaoVotacaoDTO);
		
		return new ResponseEntity<>(sessaoVotacao, HttpStatus.CREATED);
	}
    
	
    @PutMapping("/votar/{voto}/{sessao}/{pauta}")
   	public ResponseEntity<SessaoVotacao> votar(
   			@PathVariable(name = "voto") String voto, 
   			@PathVariable(name = "sessao") Long sessaoId,
   			@PathVariable(name = "pauta") Long pautaId) {
   	   
    	SessaoVotacao sessaoVotacao = this.sessaoVotacaoService.votar(voto, sessaoId, pautaId);
   		
   		return new ResponseEntity<>(sessaoVotacao, HttpStatus.CREATED);
   	}
    
   
	
}
