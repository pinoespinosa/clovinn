package com.clovinn.kardex.exception;

public class InvalidProductException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * To handle a get for an invalid Product
	 */
	public InvalidProductException() {
		super("El producto seleccionado es invalido o fue removido.");
	}

}
