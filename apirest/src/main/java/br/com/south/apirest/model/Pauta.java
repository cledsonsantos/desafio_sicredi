package br.com.south.apirest.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
*
* @author Cledson
*/


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_pauta")
public class Pauta implements Serializable {

   /**
	 * 
	 */
	private static final long serialVersionUID = 2913374271636287541L;

   @Id    
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   
   @Column(name = "descricao", nullable = false)
   private String descricaoPauta;
   private Long totalVotosSim;
   private Long totalVotosNao;

}
