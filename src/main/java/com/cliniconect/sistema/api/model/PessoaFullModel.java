package com.cliniconect.sistema.api.model;

import java.util.ArrayList;
import java.util.List;

public class PessoaFullModel {
	private Long id;
	private String nome;
	private String sexo;
	private String cpf;
	private String email;
	private String celular;
	private String dataNascimento;

	private List<EnderecoModel> enderecos = new ArrayList<>();

	public PessoaFullModel() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public List<EnderecoModel> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<EnderecoModel> enderecos) {
		this.enderecos = enderecos;
	}

}
