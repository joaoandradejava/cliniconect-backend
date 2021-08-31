package com.cliniconect.sistema.api.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cliniconect.sistema.api.input.EnderecoInput;

import com.cliniconect.sistema.domain.model.Endereco;

@Component
public class EnderecoInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Endereco toDomainModel(EnderecoInput enderecoInput) {
		return modelMapper.map(enderecoInput, Endereco.class);
	}

	public void copyToDomainModel(EnderecoInput enderecoInput, Endereco endereco) {
		modelMapper.map(enderecoInput, endereco);
	}
}
