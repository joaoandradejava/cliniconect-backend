package com.cliniconect.sistema.api.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class EnderecoInput {

	@Size(max = 255)
	@NotBlank
	private String rua;

	@Size(max = 255)
	@NotBlank
	private String numero;

	@Size(max = 255)
	@NotBlank
	private String bairro;

	@Size(max = 255)
	@NotBlank
	private String cidade;

	@Size(max = 255)
	@NotBlank
	private String estado;

	public EnderecoInput() {
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
