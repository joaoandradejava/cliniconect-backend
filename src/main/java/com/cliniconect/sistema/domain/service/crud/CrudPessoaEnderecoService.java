package com.cliniconect.sistema.domain.service.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cliniconect.sistema.domain.exception.EnderecoNaoEncontradoException;
import com.cliniconect.sistema.domain.exception.SistemaException;
import com.cliniconect.sistema.domain.model.Endereco;
import com.cliniconect.sistema.domain.repository.EnderecoRepository;

@Service
public class CrudPessoaEnderecoService {

	@Autowired
	private CrudPessoaService crudPessoaService;

	@Autowired
	private EnderecoRepository enderecoRepository;

	public Endereco buscarEnderecoDaPessoa(Long pessoaId, Long enderecoId) {
		crudPessoaService.buscarPorId(pessoaId);
		enderecoRepository.findById(enderecoId).orElseThrow(() -> new EnderecoNaoEncontradoException(enderecoId));

		return enderecoRepository.buscarEnderecoDaPessoa(pessoaId, enderecoId).orElseThrow(() -> new SistemaException(
				String.format("O Endereço de id %d não estar associado com a Pessoa de id %d", enderecoId, pessoaId)));
	}

	@Transactional
	public Endereco salvar(Endereco endereco, Long pessoaId) {
		endereco.setPessoa(crudPessoaService.buscarPorId(pessoaId));

		return enderecoRepository.save(endereco);
	}

	@Transactional
	public Endereco atualizar(Endereco endereco) {
		return enderecoRepository.save(endereco);
	}

	@Transactional
	public void deletarEnderecoDaPessoa(Long pessoaId, Long enderecoId) {
		buscarEnderecoDaPessoa(pessoaId, enderecoId);

		enderecoRepository.deleteById(enderecoId);
	}

}
