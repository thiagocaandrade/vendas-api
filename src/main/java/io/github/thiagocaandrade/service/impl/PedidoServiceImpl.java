package io.github.thiagocaandrade.service.impl;

import io.github.thiagocaandrade.domain.entity.Pedido;
import io.github.thiagocaandrade.domain.repository.Pedidos;
import io.github.thiagocaandrade.rest.dto.PedidoDTO;
import io.github.thiagocaandrade.service.PedidoService;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {

    private Pedidos repository;

    public PedidoServiceImpl(Pedidos repository) {
        this.repository = repository;
    }

    @Override
    public Pedido salvar(PedidoDTO dto) {
        Pedido pedido = new Pedido();
        Integer idCliente = dto.getCliente();
        return null;
    }
}
