package com.cliniconect.sistema.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cliniconect.sistema.api.model.PessoaFullModel;
import com.cliniconect.sistema.domain.model.Pessoa;

@Component
public class PessoaFullModelAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public PessoaFullModel toModel(Pessoa pessoa) {
		return modelMapper.map(pessoa, PessoaFullModel.class);
	}
}
