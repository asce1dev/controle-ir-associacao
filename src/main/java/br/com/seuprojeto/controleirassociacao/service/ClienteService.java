package br.com.seuprojeto.controleirassociacao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.seuprojeto.controleirassociacao.model.Cliente;
import br.com.seuprojeto.controleirassociacao.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public List<Cliente> listarClientes() {
		return clienteRepository.findAll();
	}
	
//	public Cliente salvarCliente(Cliente cliente) {
//		return clienteRepository.save(cliente);
//	}
	
	public Cliente salvarCliente(Cliente cliente) {
	    System.out.println(cliente);
	    return clienteRepository.save(cliente);
	}

	public void deletarCliente(Long id) {
		try {
			clienteRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new RuntimeException("Cliente não encontrado");
		} catch (DataIntegrityViolationException e) {
			throw new RuntimeException("Cliente em uso");
		}
	}
	
	public Cliente buscarOuFalhar(Long clienteId) {
		return clienteRepository.findById(clienteId)
				.orElseThrow(() -> new RuntimeException());
	}
}
