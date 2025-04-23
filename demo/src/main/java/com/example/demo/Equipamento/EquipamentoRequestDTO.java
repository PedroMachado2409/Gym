package com.example.demo.Equipamento;

import java.math.BigDecimal;
import java.time.LocalDate;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EquipamentoRequestDTO {

    @NotNull(message = "Nome é obrigatorio")
    private String nome;
    @NotNull(message = "carga é obrigatorio")
    private BigDecimal carga;
    
    private LocalDate dtCadastro;
    
    @NotNull(message = "Data de ultima manutenção é obrigatória")
    private LocalDate ultimaManutencao;
   
    private LocalDate proximaManutencao;

}
