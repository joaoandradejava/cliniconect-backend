package com.cliniconect.sistema.api.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cliniconect.sistema.api.input.PessoaInput;

import com.cliniconect.sistema.domain.model.Pessoa;

@Component
public class PessoaInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Pessoa toDomainModel(PessoaInput pessoaInput) {
		return modelMapper.map(pessoaInput, Pessoa.class);
	}

	public void copyToDomainModel(PessoaInput pessoaInput, Pessoa pessoa) {
		modelMapper.map(pessoaInput, pessoa);
	}
}
