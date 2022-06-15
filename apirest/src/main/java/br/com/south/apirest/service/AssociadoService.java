package br.com.south.apirest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.south.apirest.model.Associado;
import br.com.south.apirest.repository.AssociadoRepository;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class AssociadoService {
	
	private final AssociadoRepository associadoRepository;

	public List<Associado> findAllAssociados() {
		return associadoRepository.findAll();
	}

	public Associado salvar(Associado associado) {
		return  associadoRepository.save(associado);
	}

 
	
}


