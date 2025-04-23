package com.example.demo.Despesas;

import java.math.BigDecimal;
import java.time.LocalDate;


import lombok.Getter;

@Getter
public class DespesaResponseDTO {

    private Long id;
    private String descricao;
    private BigDecimal vlTotal;
    private Long userId;
    private Boolean stBaixado = false; 
    private LocalDate dtCadastro;

    public DespesaResponseDTO(Long id, String descricao, BigDecimal vlTotal, Long userId, Boolean stBaixado, LocalDate dtCadastro) {

        this.id = id;
        this.descricao = descricao;
        this.vlTotal = vlTotal;
        this.userId = userId;
        this.stBaixado = stBaixado;
        this.dtCadastro = dtCadastro;

    }

}
