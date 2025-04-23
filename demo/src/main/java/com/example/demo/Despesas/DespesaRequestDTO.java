package com.example.demo.Despesas;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.example.demo.Users.User;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DespesaRequestDTO {
    private Long id;
    private UUID codigo = UUID.randomUUID();

    @NotNull(message = "Descricao é obrigatório")
    private String descricao;
    @NotNull(message = "vlTotal é obrigatório")
    private BigDecimal vlTotal;
   
    private User userId;
    private Boolean stBaixado = false;
    private LocalDate dtCadastro;
}
