package io.github.thiagocaandrade.service;

import io.github.thiagocaandrade.domain.entity.Pedido;
import io.github.thiagocaandrade.rest.dto.PedidoDTO;

public interface PedidoService {

    Pedido salvar(PedidoDTO dto);

}
