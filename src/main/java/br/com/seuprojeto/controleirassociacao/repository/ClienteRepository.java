package br.com.seuprojeto.controleirassociacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.seuprojeto.controleirassociacao.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
}
