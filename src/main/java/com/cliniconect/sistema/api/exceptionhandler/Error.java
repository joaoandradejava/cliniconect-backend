package com.cliniconect.sistema.api.exceptionhandler;

public enum Error {
	SISTEMA_EXCEPTION("sistema-exception", "Ocorreu um erro do lado do client-side(front)"),
	ENTIDADE_NAO_ENCONTRADA("entidade-nao-encontrada-exception", "Entidade não encontrada"),
	ERRO_VALIDACAO_DOS_DADOS("erro-validacao-dos-dados", "Erro de validação dos dados"),
	ERRO_DESSERIALIZACAO_JSON("erro-desserializacao-json", "Erro na Desserialização do JSON"),
	CAMPO_NAO_RECONHECIDO("campo-nao-reconhecido", "Campo não reconhecido"),
	ENDPOINT_NAO_ENCONTRADO("endpoint-nao-encontrado", "Endpoint não encontrado"),
	METODO_NAO_SUPORTADO("metodo-nao-suportado", "Método não suportado"),
	ERRO_INTERNO_NO_SERVIDOR("erro-interno-no-servidor", "Erro interno no servidor");

	private String type;
	private String title;

	private Error(String type, String title) {
		this.type = "https://www.cliconect.com.br/" + type;
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public String getTitle() {
		return title;
	}

}
