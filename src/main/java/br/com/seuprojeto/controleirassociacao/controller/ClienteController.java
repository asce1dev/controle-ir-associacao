package br.com.seuprojeto.controleirassociacao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.seuprojeto.controleirassociacao.model.Cliente;
import br.com.seuprojeto.controleirassociacao.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public List<Cliente> listarClientes() {
		List<Cliente> todosClientes = clienteService.listarClientes();
		
		return todosClientes;
	}
	
//	@PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
//	public Cliente salvarCliente(@RequestBody Cliente cliente){
//			return clienteService.salvarCliente(cliente);
//	}
	
	@PostMapping
	public ResponseEntity<Cliente> salvarCliente(@RequestBody Cliente cliente){
	    Cliente salvo = clienteService.salvarCliente(cliente);
	    return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	}
	
	@PutMapping("/{clienteId}")
	public Cliente atualizar(@PathVariable Long clienteId,
			@RequestBody Cliente cliente) {
			
			Cliente clienteAtual = clienteService.buscarOuFalhar(clienteId);
			
			clienteAtual.setNome(cliente.getNome());
			clienteAtual.setStatus(cliente.getStatus());
			clienteAtual.setTipo(cliente.getTipo());
			
			clienteService.salvarCliente(clienteAtual);
			
			return clienteAtual;
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarCliente(@PathVariable Long id) {
		clienteService.deletarCliente(id);
	}
}
