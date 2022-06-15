package br.com.south.apirest.exception;

public class ApiGenericException  extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7311617224489960017L;

	public ApiGenericException(String msg) {
		super(msg);
	}
}
