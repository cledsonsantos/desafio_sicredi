package br.com.south.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.south.apirest.model.Associado;

@Repository
public interface AssociadoRepository  extends JpaRepository<Associado, Long> {
	

}
