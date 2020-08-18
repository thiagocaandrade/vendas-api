package io.github.thiagocaandrade.exception;

public class SenhaInvalidaException extends RuntimeException {

    public SenhaInvalidaException(){
        super("Senha inválida");
    }

}
