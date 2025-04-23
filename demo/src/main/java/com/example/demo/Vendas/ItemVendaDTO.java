package com.example.demo.Vendas;

import java.math.BigDecimal;

import lombok.Getter;

@Getter
public class ItemVendaDTO {

    private Long produtoId;
    private String produtoNome;
    private Integer quantidade;
    private BigDecimal precoUnitario;
    private BigDecimal total;

    public ItemVendaDTO(Long produtoId, String produtoNome, Integer quantidade, BigDecimal precoUnitario, BigDecimal total) {
        this.produtoId = produtoId;
        this.produtoNome = produtoNome;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.total = total;
    }
}