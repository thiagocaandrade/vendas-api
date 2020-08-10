package io.github.thiagocaandrade.rest.controller;


import io.github.thiagocaandrade.domain.entity.Pedido;
import io.github.thiagocaandrade.rest.dto.PedidoDTO;
import io.github.thiagocaandrade.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoControler {

    private PedidoService service;

    public PedidoControler(PedidoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody @Valid PedidoDTO dto){
        Pedido pedido = service.salvar(dto);
        return pedido.getId();

    }
}
