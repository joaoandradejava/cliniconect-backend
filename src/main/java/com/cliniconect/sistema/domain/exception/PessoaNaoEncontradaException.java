package com.cliniconect.sistema.domain.exception;

public class PessoaNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public PessoaNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	public PessoaNaoEncontradaException(Long id) {
		super(String.format("A Pessoa de id %d não foi encontrada no sistema!", id));
	}
}
