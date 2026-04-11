package br.com.marketplace.api.exception;

// Uma exceção personalizada que herda do RuntimeException do Java
public class ApiGlobalAdviceException extends RuntimeException {
    
    public ApiGlobalAdviceException(String mensagem) {
        super(mensagem);
    }
}