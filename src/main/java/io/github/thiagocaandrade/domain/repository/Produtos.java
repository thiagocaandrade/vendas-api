package io.github.thiagocaandrade.domain.repository;

import io.github.thiagocaandrade.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtos extends JpaRepository<Produto, Integer> {
}
