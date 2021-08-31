package com.cliniconect.sistema.api.input;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.cliniconect.sistema.domain.model.enumeration.Sexo;

public class PessoaInput {

	@Size(max = 255)
	@NotBlank
	private String nome;

	@NotNull
	private Sexo sexo;

	@Email
	@Size(max = 255)
	@NotBlank
	private String email;

	@CPF
	@NotBlank
	private String cpf;

	@Size(max = 11)
	@NotBlank
	private String celular;

	@PastOrPresent
	@NotNull
	private LocalDate dataNascimento;

	public PessoaInput() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

}
