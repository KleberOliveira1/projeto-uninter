package com.raizesdonordeste.projetobackend.service;

import com.raizesdonordeste.projetobackend.dto.PagamentoRequest;
import com.raizesdonordeste.projetobackend.model.*;
import com.raizesdonordeste.projetobackend.repository.PagamentoRepository;
import com.raizesdonordeste.projetobackend.repository.PedidoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final PedidoRepository pedidoRepository;

    public PagamentoService(PagamentoRepository pagamentoRepository, PedidoRepository pedidoRepository) {
        this.pagamentoRepository = pagamentoRepository;
        this.pedidoRepository = pedidoRepository;
    }

    public Pagamento pagarPedido(UUID pedidoId, PagamentoRequest request) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));

        Pagamento pagamento = new Pagamento();
        pagamento.setPedido(pedido);

        pagamento.setFormaPagamento(FormaPagamento.valueOf(request.formaPagamento));

        pagamento.setStatusPagamento(StatusPagamento.APROVADO);

        pagamento.setValor(pedido.getValorPedido());

        pagamento.setCodigoTransacao(UUID.randomUUID().toString());

        pedido.setStatusPedido(StatusPedido.PAGO);

        pedidoRepository.save(pedido);

        return pagamentoRepository.save(pagamento);
    }


}