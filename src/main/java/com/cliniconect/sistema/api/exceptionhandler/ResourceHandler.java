package com.cliniconect.sistema.api.exceptionhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cliniconect.sistema.domain.exception.EntidadeNaoEncontradaException;
import com.cliniconect.sistema.domain.exception.SistemaException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

@ControllerAdvice
public class ResourceHandler extends ResponseEntityExceptionHandler {

	private static final String MENSAGEM_PADRAO_ERRO_INESPERADO = "Ocorreu um erro inesperado, se o problema persistir recomendo que entre em contato com desenvolvedor da API.";

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleErroInternoNoServidor(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		Error error = Error.ERRO_INTERNO_NO_SERVIDOR;
		String message = "Aconteceu um erro interno no servidor";
		ProblemDetail problemDetail = new ProblemDetail(error.getType(), error.getTitle(), status.value(), message,
				MENSAGEM_PADRAO_ERRO_INESPERADO);

		return handleExceptionInternal(ex, problemDetail, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(SistemaException.class)
	public ResponseEntity<Object> handleSistema(SistemaException ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Error error = Error.SISTEMA_EXCEPTION;
		ProblemDetail problemDetail = new ProblemDetail(error.getType(), error.getTitle(), status.value(),
				ex.getMessage(), ex.getMessage());

		return handleExceptionInternal(ex, problemDetail, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<Object> handleEntidadeNaoEncontrada(EntidadeNaoEncontradaException ex, WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		Error error = Error.ENTIDADE_NAO_ENCONTRADA;
		String message = ex.getMessage();
		ProblemDetail problemDetail = new ProblemDetail(error.getType(), error.getTitle(), status.value(), message,
				message);

		return handleExceptionInternal(ex, problemDetail, new HttpHeaders(), status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Error error = Error.ERRO_VALIDACAO_DOS_DADOS;
		String message = "Erro de validação dos dados vindo do corpo da requisição, verifique se todos os campos foram preenchidos corretamente";
		ProblemDetail problemDetail = new ProblemDetail(error.getType(), error.getTitle(), status.value(), message,
				"Erro de validação");

		for (ObjectError objectError : ex.getAllErrors()) {
			String field = objectError.getObjectName();

			if (objectError instanceof FieldError) {
				field = ((FieldError) objectError).getField();
			}

			String userMessage = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());

			problemDetail.adicionarError(field, userMessage);
		}

		return handleExceptionInternal(ex, problemDetail, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Throwable cause = ex.getCause();

		if (cause instanceof UnrecognizedPropertyException) {
			return handleUnrecognizedProperty((UnrecognizedPropertyException) cause, headers, status, request);
		}

		Error error = Error.ERRO_DESSERIALIZACAO_JSON;
		String message = "Ocorreu um erro na desserialização do JSON. Verifique se a sintaxe do JSON foi inserida corretamente.";
		ProblemDetail problemDetail = new ProblemDetail(error.getType(), error.getTitle(), status.value(), message,
				MENSAGEM_PADRAO_ERRO_INESPERADO);

		return handleExceptionInternal(ex, problemDetail, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		Error error = Error.ENDPOINT_NAO_ENCONTRADO;
		String message = String.format("Não foi encontrado nenhum endpoint para o recurso [%S%s]", ex.getHttpMethod(),
				ex.getRequestURL());
		ProblemDetail problemDetail = new ProblemDetail(error.getType(), error.getTitle(), status.value(), message,
				MENSAGEM_PADRAO_ERRO_INESPERADO);

		return handleExceptionInternal(ex, problemDetail, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Error error = Error.METODO_NAO_SUPORTADO;
		String message = String.format("Método de solicitação '%s' não suportado", ex.getMethod());
		ProblemDetail problemDetail = new ProblemDetail(error.getType(), error.getTitle(), status.value(), message,
				MENSAGEM_PADRAO_ERRO_INESPERADO);

		return handleExceptionInternal(ex, problemDetail, headers, status, request);
	}

	private ResponseEntity<Object> handleUnrecognizedProperty(UnrecognizedPropertyException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		Error error = Error.CAMPO_NAO_RECONHECIDO;
		String message = String.format("O campo '%s' vindo do corpo da requisição é inexistente", ex.getPropertyName());
		ProblemDetail problemDetail = new ProblemDetail(error.getType(), error.getTitle(), status.value(), message,
				MENSAGEM_PADRAO_ERRO_INESPERADO);

		return handleExceptionInternal(ex, problemDetail, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		if (body == null) {
			body = new ProblemDetail(null, null, status.value(), status.getReasonPhrase(),
					MENSAGEM_PADRAO_ERRO_INESPERADO);
		}

		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
}
