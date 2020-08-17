package io.github.thiagocaandrade.service;

import io.github.thiagocaandrade.domain.entity.Pedido;
import io.github.thiagocaandrade.domain.enums.StatusPedido;
import io.github.thiagocaandrade.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {

    Pedido salvar(PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(Integer id);

    void atualizaStatus(Integer id, StatusPedido statusPedido);

}
