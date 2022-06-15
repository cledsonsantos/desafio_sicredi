package br.com.south.apirest.dto;

import lombok.Data;

/**
*
* @author Cledson
*/


@Data
public class PautaDTO  {

   private String descricaoPauta;
   private Long totalVotosSim;
   private Long totalVotosNao;

}
