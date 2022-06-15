package br.com.south.apirest.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.south.apirest.model.Voto;
import lombok.Data;


@Data
public class SessaoVotacaoDTO  {
   
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private LocalDate dataRegistro;
    private Long associado;
    private Long pauta;
    private Boolean jaVotado;
    private Voto voto;
    private Long duracao;
    private LocalDateTime encerramento;
    
    
}