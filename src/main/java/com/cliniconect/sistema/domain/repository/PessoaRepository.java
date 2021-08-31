package com.cliniconect.sistema.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cliniconect.sistema.domain.model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	Optional<Pessoa> findByEmail(String email);

	Optional<Pessoa> findByCpf(String cpf);

	@Query("select p from Pessoa p where lower(p.nome) like lower(concat('%', ?1, '%'))")
	Page<Pessoa> buscarPorNome(String nome, Pageable pageable);

	@Query("select p from Pessoa p where p.cpf like %?1%")
	Page<Pessoa> buscarPorCpf(String cpf, Pageable pageable);

}
