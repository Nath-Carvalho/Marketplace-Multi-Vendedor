package br.com.marketplace.api.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice 
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiGlobalAdviceException.class)
    public String tratarRegraDeNegocio(ApiGlobalAdviceException ex, Model model) {
        model.addAttribute("mensagemErro", ex.getMessage());
        return "error/500"; 
    }

    @ExceptionHandler(Exception.class)
    public String tratarErroGenerico(Exception ex, Model model) {
        model.addAttribute("mensagemErro", "Ops! Algo deu errado internamente: " + ex.getMessage());
        return "error/500";
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public String tratarErro404(NoResourceFoundException ex) {
        return "error/404"; 
    }
}