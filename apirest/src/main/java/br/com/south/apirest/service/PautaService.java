package br.com.south.apirest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.south.apirest.model.Pauta;
import br.com.south.apirest.repository.PautaRepository;
import lombok.RequiredArgsConstructor;

	
@Service
@RequiredArgsConstructor
public class PautaService {
	
	private final PautaRepository pautaRepository;
    
    public List<Pauta> findAll() {
        return pautaRepository.findAll();
    }

    public Pauta salvar(Pauta pauta) {
		return  pautaRepository.save(pauta);
	}

}
 	