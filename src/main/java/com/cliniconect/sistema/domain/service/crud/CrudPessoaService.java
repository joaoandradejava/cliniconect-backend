package com.cliniconect.sistema.domain.service.crud;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cliniconect.sistema.domain.exception.PessoaNaoEncontradaException;
import com.cliniconect.sistema.domain.model.Pessoa;
import com.cliniconect.sistema.domain.repository.PessoaRepository;
import com.cliniconect.sistema.domain.service.validation.PessoaCpfUnicoValidation;
import com.cliniconect.sistema.domain.service.validation.PessoaEmailUnicoValidation;

@Service
public class CrudPessoaService {

	@Autowired
	private PessoaRepository repository;

	@Autowired
	private PessoaEmailUnicoValidation pessoaEmailUnicoValidation;

	@Autowired
	private PessoaCpfUnicoValidation pessoaCpfUnicoValidation;

	@Autowired
	private EntityManager entityManager;

	public Page<Pessoa> buscarTodas(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public Page<Pessoa> buscarPorNome(String nome, Pageable pageable) {
		return repository.buscarPorNome(nome, pageable);
	}

	public Page<Pessoa> buscarPorCpf(String cpf, Pageable pageable) {
		return repository.buscarPorCpf(cpf, pageable);
	}

	public Pessoa buscarPorId(Long id) {
		return repository.findById(id).orElseThrow(() -> new PessoaNaoEncontradaException(id));
	}

	@Transactional
	public Pessoa salvar(Pessoa pessoa) {
		pessoaEmailUnicoValidation.validarEmailUnico(pessoa);
		pessoaCpfUnicoValidation.validarCpfUnico(pessoa);

		return repository.save(pessoa);
	}

	@Transactional
	public Pessoa atualizar(Pessoa pessoa) {
		entityManager.detach(pessoa);
		pessoaEmailUnicoValidation.validarEmailUnico(pessoa);
		pessoaCpfUnicoValidation.validarCpfUnico(pessoa);

		return repository.save(pessoa);
	}

	@Transactional
	public void deletarPorId(Long id) {
		buscarPorId(id);

		repository.deleteById(id);
	}

}
