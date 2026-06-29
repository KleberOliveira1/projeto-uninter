package com.raizesdonordeste.projetobackend.controller;

import com.raizesdonordeste.projetobackend.dto.PagamentoRequest;
import com.raizesdonordeste.projetobackend.model.Pagamento;
import com.raizesdonordeste.projetobackend.service.PagamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @PostMapping("/{pedidoId}/pagar")
    @PreAuthorize("hasAnyRole('ADMIN','GERENTE','ATENDENTE', 'CLIENTE')")
    @Operation(summary = "Altera o status do pedido para preparar")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pagamento efetuado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Não autorizado"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro no servidor"),
    })
    public ResponseEntity<Pagamento> pagarPedido(
            @PathVariable UUID pedidoId,
            @RequestBody PagamentoRequest request){

        Pagamento pagamento = pagamentoService.pagarPedido(pedidoId, request);

        return ResponseEntity.ok(pagamento);

    }
}