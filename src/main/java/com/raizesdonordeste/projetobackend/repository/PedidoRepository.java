package com.raizesdonordeste.projetobackend.repository;

import com.raizesdonordeste.projetobackend.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PedidoRepository extends JpaRepository<Pedido, UUID> {
}
