package io.github.thiagocaandrade.rest.controller;

import io.github.thiagocaandrade.domain.entity.Pedido;
import io.github.thiagocaandrade.exception.PedidoNaoEncontradoException;
import io.github.thiagocaandrade.exception.RegraNegocioException;
import io.github.thiagocaandrade.rest.ApiErros;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class AplicationControllerAdvice {


    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErros handleRegraNegocioException(RegraNegocioException ex){
        String mensagemErro = ex.getMessage();
        return new ApiErros(mensagemErro);
    }

    @ExceptionHandler(PedidoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErros handlePedidoNotFoundException(PedidoNaoEncontradoException ex){
        return new ApiErros(ex.getMessage());

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErros handleMethodNotValidException(MethodArgumentNotValidException ex){
        List<String> erros = ex.getBindingResult()
                .getAllErrors().stream()
                .map(erro ->
                        erro.getDefaultMessage())
                .collect(Collectors.toList());

        return new ApiErros(erros);

    }
}
