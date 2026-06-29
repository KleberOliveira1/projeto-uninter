package com.raizesdonordeste.projetobackend.repository;

import com.raizesdonordeste.projetobackend.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProdutoRepository extends JpaRepository<Produto, UUID> {

}
