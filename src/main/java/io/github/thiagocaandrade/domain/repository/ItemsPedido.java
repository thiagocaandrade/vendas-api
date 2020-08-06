package io.github.thiagocaandrade.domain.repository;

import io.github.thiagocaandrade.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsPedido extends JpaRepository<ItemPedido, Integer> {
}
