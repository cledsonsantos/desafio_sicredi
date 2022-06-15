package br.com.south.apirest.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.south.apirest.apiConsumer.APIConsumerCpf;
import br.com.south.apirest.dto.SessaoVotacaoDTO;
import br.com.south.apirest.exception.ApiGenericException;
import br.com.south.apirest.model.Associado;
import br.com.south.apirest.model.Pauta;
import br.com.south.apirest.model.SessaoVotacao;
import br.com.south.apirest.model.Voto;
import br.com.south.apirest.repository.AssociadoRepository;
import br.com.south.apirest.repository.PautaRepository;
import br.com.south.apirest.repository.SessaoVotacaoRepository;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class SessaoVotacaoService {
	
	private static final String CPF_UNABLE_TO_VOTE = "UNABLE_TO_VOTE";
	private final AssociadoRepository associadoRepository;
	private final PautaRepository pautaRepository;
	private final SessaoVotacaoRepository sessaoVotacaoRepository;
//	private static final long MINUTO = 1000 * 60; // 1 minuto
	private static final long MINUTO = 10000; // 1 minuto
	private final APIConsumerCpf aPIConsumerCpf;

	public List<SessaoVotacao> findAllSessao() {
		return sessaoVotacaoRepository.findAll();
	}
	
	
    public SessaoVotacao salvar(SessaoVotacao sessaoVotacao) {
		return  sessaoVotacaoRepository.save(sessaoVotacao);
	}
 
	 
	public SessaoVotacao abrirSessaoParaVotacao(SessaoVotacaoDTO sessaoVotacaoDTO) {
		LocalDateTime dataEncerramento = LocalDateTime.now(); 
		 SessaoVotacao sessaoVotacao = new SessaoVotacao();
		
		if (sessaoVotacaoDTO.getDuracao() != null && sessaoVotacaoDTO.getDuracao().intValue() != 0) {
			dataEncerramento = dataEncerramento.plusMinutes(sessaoVotacaoDTO.getDuracao()); 
			
		} else {
            dataEncerramento = dataEncerramento.plusMinutes(MINUTO); 
            sessaoVotacao.setDuracao(MINUTO);
		}
		

        sessaoVotacao.setDuracao(MINUTO);
		sessaoVotacao.setEncerramento(dataEncerramento.plusMinutes(MINUTO));
		sessaoVotacao.setDataRegistro(LocalDate.now());
		sessaoVotacao.setPauta(getPauta(sessaoVotacaoDTO.getPauta()));
		sessaoVotacao.setAssociado(getAssociado(sessaoVotacaoDTO.getAssociado()));
		SessaoVotacao sessaoSalva = sessaoVotacaoRepository.save(sessaoVotacao);
		
		return sessaoSalva;
	}

	
	public Pauta getPauta(Long pautaId) {
		Optional<Pauta>  pautaOptional = pautaRepository.findById(pautaId);
		Pauta pauta = pautaOptional.get();
		
		return pauta;
	}
	
	public Associado getAssociado(Long associadoId) {
		Optional<Associado>  associadoOptional = associadoRepository.findById(associadoId);
		Associado associado = associadoOptional.get();
		
		return associado;
	}
	
	public Voto getVoto(String voto) {
		List<Voto> lista = Arrays.asList(Voto.values());

		for (Voto vt : lista) {
			if (vt.toString().equals(voto)) {
				return vt;
			}
		}
		return null;
	}
	
	
	public SessaoVotacao votar(String voto, Long sessaId, Long pautaId) {
		Optional<SessaoVotacao>  sessaoVotacaoOptional = sessaoVotacaoRepository.findById(sessaId);
		SessaoVotacao sessaoVotacao = sessaoVotacaoOptional.get();
		
		Optional<Pauta>  pautaOptional = pautaRepository.findById(pautaId);
		Pauta pauta = pautaOptional.get();
		
		if (Voto.SIM.equals(voto.toUpperCase())) {
			sessaoVotacao.setVoto(Voto.SIM);
		} else {
			sessaoVotacao.setVoto(Voto.NAO);
		}
		
		if (pautaOptional.isPresent() && sessaoVotacaoOptional.isPresent()) {
			 throw new ApiGenericException(String.format("A Pauta e a Sessão de Votação deve existir !"));
			
		} else {
			if (pauta.getId() == sessaoVotacao.getPauta().getId()) {
				Boolean isCpfOK = validarCpfAssociado(sessaoVotacao.getAssociado().getCpf());

				if (isCpfOK) {
					sessaoVotacaoRepository.save(sessaoVotacao);
						
				} else {
					 throw new ApiGenericException(String.format("Este Associado não tem permissão para votar !"));
				}
				
				
			} else {
				throw new ApiGenericException(String.format("A Pauta é diferente da Sessão de Votação informada !"));
			}
			
		}
		
		return sessaoVotacao;
	}

	private Boolean validarCpfAssociado(String cpf) {
//		if (cpf == null || cpf.isBlank()) {
//			throw new ApiGenericException("CPF deve ser informado!");
//		}
//		String cpfFormated = null;
//		
//		 if (ValidaCPF.isCPF(cpf) == true) {
//            System.out.printf("%s\n", ValidaCPF.imprimeCPF(cpf));
//		 	cpfFormated = ValidaCPF.imprimeCPF(cpf);
//		 
//		 } else {
//			 throw new ApiGenericException("CPF inválido !");
//		 }
//			 
//		
//		try {
//			String cpfResponse = cpfConsumerAPI.pesquisaPorCpf(cpfFormated);
//			
//			if (CPF_UNABLE_TO_VOTE.equals(cpfResponse)) {
//				return Boolean.FALSE;
//
//			} else {
				return Boolean.TRUE;
//			}
//			
//		} catch (FeignException e) {
//			throw new ApiGenericException("Ocorreu um erro, tente novamente mais tarde!" + e.getMessage());
//		}
	}
	
	
	

}


