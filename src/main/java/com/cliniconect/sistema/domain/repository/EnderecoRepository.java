package com.cliniconect.sistema.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cliniconect.sistema.domain.model.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

	@Query("select e from Endereco e where e.pessoa.id = ?1 and e.id = ?2")
	Optional<Endereco> buscarEnderecoDaPessoa(Long pessoaId, Long enderecoId);
}
