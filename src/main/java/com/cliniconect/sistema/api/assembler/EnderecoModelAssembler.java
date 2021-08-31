package com.cliniconect.sistema.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cliniconect.sistema.api.model.EnderecoModel;
import com.cliniconect.sistema.domain.model.Endereco;

@Component
public class EnderecoModelAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public EnderecoModel toModel(Endereco endereco) {
		return modelMapper.map(endereco, EnderecoModel.class);
	}

	public List<EnderecoModel> toCollectionModel(List<Endereco> lista) {
		return lista.stream().map(x -> toModel(x)).collect(Collectors.toList());
	}
}
