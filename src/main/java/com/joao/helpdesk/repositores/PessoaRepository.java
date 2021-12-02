package com.joao.helpdesk.repositores;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joao.helpdesk.domain.Pessoa;
//vai fazer a persitencia la no banco 


public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

	Optional<Pessoa> findByCpf(String cpf);
	Optional<Pessoa> findByEmail(String email);

}
