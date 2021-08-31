package com.cliniconect.sistema.domain.service.validation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cliniconect.sistema.domain.exception.SistemaException;
import com.cliniconect.sistema.domain.model.Pessoa;
import com.cliniconect.sistema.domain.repository.PessoaRepository;

@Component
public class PessoaEmailUnicoValidation {

	@Autowired
	private PessoaRepository repository;

	public void validarEmailUnico(Pessoa pessoa) {
		Optional<Pessoa> obj = repository.findByEmail(pessoa.getEmail());

		if (obj.isPresent() && !obj.get().equals(pessoa)) {
			throw new SistemaException(
					String.format("Já existe o email '%s' cadastrado no sistema!", pessoa.getEmail()));
		}
	}

}
