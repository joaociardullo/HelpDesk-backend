package com.joao.helpdesk.resources.exceptions;
/*
 * 
 * Classe parA corrigir o erro no 	POSTMAN, FAZ QUE NAO FICA MUITO TEXTO NO POSTMAN QUANDO NAO EXISTIR O iD
 * 
 *  tratamaento de exceção de objeto nao encontrado personalizado
 * 
 * */

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.joao.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.joao.helpdesk.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourcesExeceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFoudExcption(ObjectNotFoundException ex, HttpServletRequest request) {

		StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
				"object Not Found", ex.getMessage(), request.getRequestURI());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	/*
	 * MOSTRANDO A MENSAGEM QUANDO O CPF E EMAIL JA EXIETE
	 *
	 */

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException ex,
			HttpServletRequest request) {

		StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				"Violação de Dados", ex.getMessage(), request.getRequestURI());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

}
