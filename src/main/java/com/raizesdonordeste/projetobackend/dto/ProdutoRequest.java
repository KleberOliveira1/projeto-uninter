package com.raizesdonordeste.projetobackend.dto;

public class ProdutoRequest {

    public String nome;

    public Double preco;

    public Integer estoque;

    public String getNome() {
        return nome;
    }

    public Double getPreco() {
        return preco;
    }

    public Integer getEstoque() {
        return estoque;
    }
}