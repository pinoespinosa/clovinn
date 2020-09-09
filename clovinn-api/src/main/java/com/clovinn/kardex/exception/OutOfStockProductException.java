package com.clovinn.kardex.exception;

public class OutOfStockProductException extends RuntimeException {

	/**
	 * To handle Out Of Stock of a Product
	 */
	private static final long serialVersionUID = 1L;

	public OutOfStockProductException() {
		super("El stock requerido es mayor que el disponible.");
	}
	
}
