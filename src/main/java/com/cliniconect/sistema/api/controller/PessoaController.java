package com.cliniconect.sistema.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cliniconect.sistema.api.assembler.PessoaFullModelAssembler;
import com.cliniconect.sistema.api.assembler.PessoaModelAssembler;
import com.cliniconect.sistema.api.disassembler.PessoaInputDisassembler;
import com.cliniconect.sistema.api.input.PessoaInput;
import com.cliniconect.sistema.api.model.PessoaFullModel;
import com.cliniconect.sistema.api.model.PessoaModel;
import com.cliniconect.sistema.domain.model.Pessoa;
import com.cliniconect.sistema.domain.service.crud.CrudPessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

	@Autowired
	private CrudPessoaService crudPessoaService;

	@Autowired
	private PessoaModelAssembler pessoaModelAssembler;

	@Autowired
	private PessoaFullModelAssembler pessoaFullModelAssembler;

	@Autowired
	private PessoaInputDisassembler pessoaInputDisassembler;

	@GetMapping
	public Page<PessoaModel> buscarTodas(Pageable pageable, String nome, String cpf) {
		Page<Pessoa> page = null;

		if (StringUtils.hasLength(nome)) {
			page = crudPessoaService.buscarPorNome(nome, pageable);

			return page.map(x -> pessoaModelAssembler.toModel(x));
		}

		if (StringUtils.hasLength(cpf)) {
			page = crudPessoaService.buscarPorCpf(cpf, pageable);

			return page.map(x -> pessoaModelAssembler.toModel(x));
		}

		page = crudPessoaService.buscarTodas(pageable);
		return page.map(x -> pessoaModelAssembler.toModel(x));
	}

	@GetMapping("/{id}")
	public PessoaFullModel buscarPorId(@PathVariable Long id) {
		Pessoa pessoa = crudPessoaService.buscarPorId(id);

		return pessoaFullModelAssembler.toModel(pessoa);
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public PessoaFullModel salvar(@Valid @RequestBody PessoaInput pessoaInput) {
		Pessoa pessoa = crudPessoaService.salvar(pessoaInputDisassembler.toDomainModel(pessoaInput));

		return pessoaFullModelAssembler.toModel(pessoa);
	}

	@PutMapping("/{id}")
	public PessoaFullModel atualizar(@Valid @RequestBody PessoaInput pessoaInput, @PathVariable Long id) {
		Pessoa atual = crudPessoaService.buscarPorId(id);
		pessoaInputDisassembler.copyToDomainModel(pessoaInput, atual);
		atual = crudPessoaService.atualizar(atual);

		return pessoaFullModelAssembler.toModel(atual);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deletarPorId(@PathVariable Long id) {
		crudPessoaService.deletarPorId(id);
	}

}
