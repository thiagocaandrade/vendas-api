package io.github.thiagocaandrade.service.impl;

import io.github.thiagocaandrade.domain.entity.Cliente;
import io.github.thiagocaandrade.domain.entity.ItemPedido;
import io.github.thiagocaandrade.domain.entity.Pedido;
import io.github.thiagocaandrade.domain.entity.Produto;
import io.github.thiagocaandrade.domain.enums.StatusPedido;
import io.github.thiagocaandrade.domain.repository.Clientes;
import io.github.thiagocaandrade.domain.repository.ItemsPedido;
import io.github.thiagocaandrade.domain.repository.Pedidos;
import io.github.thiagocaandrade.domain.repository.Produtos;
import io.github.thiagocaandrade.exception.PedidoNaoEncontradoException;
import io.github.thiagocaandrade.exception.RegraNegocioException;
import io.github.thiagocaandrade.rest.dto.ItemPedidoDTO;
import io.github.thiagocaandrade.rest.dto.PedidoDTO;
import io.github.thiagocaandrade.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final ItemsPedido itemsPedidoRepository;
    private final Pedidos repository;
    private final Clientes clientesRepository;
    private final Produtos produtosRepository;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {

        Integer idCliente = dto.getCliente();
        Cliente cliente = clientesRepository
                .findById(idCliente)
                .orElseThrow(() ->
                        new RegraNegocioException("Código de cliente inválido."));



        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.REALIZADO);

        List<ItemPedido> itemsPedido = converterItems(pedido, dto.getItems());
        repository.save(pedido);
        itemsPedidoRepository.saveAll(itemsPedido);
        pedido.setItens(itemsPedido);
        return pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return repository.findByIdFetchItens(id);
    }

    @Override
    public void atualizaStatus(Integer id, StatusPedido statusPedido) {
        repository
                .findById(id)
                .map(pedido -> {
                    pedido.setStatus(statusPedido);
                    return repository.save(pedido);
                }).orElseThrow(() -> new PedidoNaoEncontradoException());
    }


    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items){

        if (items.isEmpty()){
            throw  new RegraNegocioException("Não é possível realizar um pedido sem items.");
        }

        return items
                .stream()
                .map(dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtosRepository
                            .findById(idProduto)
                            .orElseThrow(() ->
                                    new RegraNegocioException("Código de produto inválido: " +idProduto
                                    ));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;

                }).collect(Collectors.toList());

    }
}
