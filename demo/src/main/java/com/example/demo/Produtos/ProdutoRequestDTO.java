package com.example.demo.Produtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoRequestDTO {

    private Long id;

    @NotNull(message = "Nome é obrigatório")
    private String nome;
    @NotNull(message = "Estoque obrigatório")
    private BigDecimal estoque = BigDecimal.ZERO;
    private LocalDate dtCadastro;
    @NotNull(message = "Unidade é obrigatório")
    private String unidade;

}
