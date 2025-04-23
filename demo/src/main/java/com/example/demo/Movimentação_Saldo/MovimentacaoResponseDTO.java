package com.example.demo.Movimentação_Saldo;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;

@Getter
public class MovimentacaoResponseDTO {

    private Long id;
    private String tipo;
    private BigDecimal valor;
    private LocalDate data;

    public MovimentacaoResponseDTO( Long id, String tipo, BigDecimal valor, LocalDate data ) {

        this.id = id;
        this.tipo = tipo;
        this.valor = valor;
        this.data = data;

    }

}
