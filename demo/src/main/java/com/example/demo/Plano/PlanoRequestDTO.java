package com.example.demo.Plano;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
public class PlanoRequestDTO {
    private Long id;
    
    @NotNull(message = "O nome é Obrigatório")
    private String nome;

    @NotNull(message = "O Valor é obrigatório")
    private BigDecimal valor;

    @NotNull(message = "A Duração é obrigatória")
    private int duracao;

    @NotNull(message = "A Descrição é obrigatória ")
    private String descricao;
}
