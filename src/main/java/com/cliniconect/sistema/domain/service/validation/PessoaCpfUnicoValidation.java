package com.cliniconect.sistema.domain.service.validation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cliniconect.sistema.domain.exception.SistemaException;
import com.cliniconect.sistema.domain.model.Pessoa;
import com.cliniconect.sistema.domain.repository.PessoaRepository;

@Component
public class PessoaCpfUnicoValidation {

	@Autowired
	private PessoaRepository repository;

	public void validarCpfUnico(Pessoa pessoa) {
		Optional<Pessoa> obj = repository.findByCpf(pessoa.getCpf());

		if (obj.isPresent() && !obj.get().equals(pessoa)) {
			throw new SistemaException(String.format("Já existe o cpf '%s' cadastrado no sistema!", pessoa.getCpf()));
		}
	}
}
