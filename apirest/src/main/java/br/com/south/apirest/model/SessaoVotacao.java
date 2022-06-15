package br.com.south.apirest.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_sessao_votacao")
public class SessaoVotacao  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1511704900052874398L;

	@Id    
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "data_registro")
    private LocalDate dataRegistro;

    @NonNull
    @ManyToOne
    private Associado associado;
     
    @ManyToOne
    private Pauta pauta;
    
    private Boolean jaVotado = Boolean.FALSE;
  
    @NonNull
    @Column(length = 3)
    @Enumerated(EnumType.STRING)
    private Voto voto;
    
    private Long duracao;
    private LocalDateTime encerramento;
    
    
}