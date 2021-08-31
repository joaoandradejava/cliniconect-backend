package com.cliniconect.sistema.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cliniconect.sistema.api.assembler.EnderecoModelAssembler;
import com.cliniconect.sistema.api.disassembler.EnderecoInputDisassembler;
import com.cliniconect.sistema.api.input.EnderecoInput;
import com.cliniconect.sistema.api.model.EnderecoModel;
import com.cliniconect.sistema.domain.model.Endereco;
import com.cliniconect.sistema.domain.model.Pessoa;
import com.cliniconect.sistema.domain.service.crud.CrudPessoaEnderecoService;
import com.cliniconect.sistema.domain.service.crud.CrudPessoaService;

@RestController
@RequestMapping("/pessoas/{pessoaId}/enderecos")
public class PessoaEnderecoController {

	@Autowired
	private CrudPessoaService crudPessoaService;

	@Autowired
	private EnderecoModelAssembler enderecoModelAssembler;

	@Autowired
	private CrudPessoaEnderecoService crudPessoaEnderecoService;

	@Autowired
	private EnderecoInputDisassembler enderecoInputDisassembler;

	@GetMapping
	public List<EnderecoModel> buscarTodosEnderecos(@PathVariable Long pessoaId) {
		Pessoa pessoa = crudPessoaService.buscarPorId(pessoaId);

		return enderecoModelAssembler.toCollectionModel(pessoa.getEnderecos());
	}

	@GetMapping("/{enderecoId}")
	public EnderecoModel buscarEnderecoDaPessoa(@PathVariable Long pessoaId, @PathVariable Long enderecoId) {
		Endereco endereco = crudPessoaEnderecoService.buscarEnderecoDaPessoa(pessoaId, enderecoId);

		return enderecoModelAssembler.toModel(endereco);
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public EnderecoModel salvar(@Valid @RequestBody EnderecoInput enderecoInput, @PathVariable Long pessoaId) {
		Endereco endereco = crudPessoaEnderecoService.salvar(enderecoInputDisassembler.toDomainModel(enderecoInput),
				pessoaId);

		return enderecoModelAssembler.toModel(endereco);
	}

	@PutMapping("/{enderecoId}")
	public EnderecoModel atualizar(@Valid @RequestBody EnderecoInput enderecoInput, @PathVariable Long pessoaId,
			@PathVariable Long enderecoId) {
		Endereco atual = crudPessoaEnderecoService.buscarEnderecoDaPessoa(pessoaId, enderecoId);
		enderecoInputDisassembler.copyToDomainModel(enderecoInput, atual);
		atual = crudPessoaEnderecoService.atualizar(atual);

		return enderecoModelAssembler.toModel(atual);
	}

	@DeleteMapping("/{enderecoId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deletarEnderecoDaPessoa(@PathVariable Long pessoaId, @PathVariable Long enderecoId) {
		crudPessoaEnderecoService.deletarEnderecoDaPessoa(pessoaId, enderecoId);
	}

}
