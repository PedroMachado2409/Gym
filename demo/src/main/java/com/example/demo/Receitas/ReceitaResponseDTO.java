package com.example.demo.Receitas;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;

@Getter
public class ReceitaResponseDTO {
    
    private Long id;
    private String descricao;
    private BigDecimal vlTotal;
    private LocalDate dtCadastro;
    private Boolean stBaixado;
    private Long userId;


    public ReceitaResponseDTO(Long id, String descricao, BigDecimal vlTotal, LocalDate dtCadastro, Boolean stBaixado, Long userId) {

        this.id = id;   
        this.descricao = descricao;
        this.vlTotal = vlTotal;
        this.dtCadastro = dtCadastro;
        this.stBaixado = stBaixado;
        this.userId = userId;
       
    }


}
