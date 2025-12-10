package br.com.campmanager.projeto.exception;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	// Construtor que aceita uma mensagem de erro
    public BusinessException(String message) {
        super(message);
    }

    // Construtor opcional que aceita mensagem e a causa (outra exceção)
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
