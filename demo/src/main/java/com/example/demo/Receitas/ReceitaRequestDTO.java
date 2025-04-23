package com.example.demo.Receitas;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.example.demo.Users.User;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReceitaRequestDTO {
    private Long id;

    private UUID codigo = UUID.randomUUID();

    @NotNull(message = "descricao é obrigatório")
    private String descricao;
    @NotNull(message = " VlTotal é obrigatório")
    private BigDecimal vlTotal;
    private User userId;
    private Boolean stBaixado = false;
    private LocalDate dtCadastro;
}
