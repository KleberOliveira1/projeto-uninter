package com.raizesdonordeste.projetobackend.repository;

import com.raizesdonordeste.projetobackend.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PagamentoRepository extends JpaRepository<Pagamento, UUID> {
}
