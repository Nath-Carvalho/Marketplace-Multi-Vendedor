package br.com.marketplace.api.exception;

public class ApiGlobalAdviceException extends RuntimeException {
    
    public ApiGlobalAdviceException(String mensagem) {
        super(mensagem);
    }
}