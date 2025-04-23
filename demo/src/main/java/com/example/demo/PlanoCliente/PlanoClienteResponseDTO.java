package com.example.demo.PlanoCliente;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlanoClienteResponseDTO {

    private Long id;
    private String nomeCliente;
    private String nomePlano;
    private BigDecimal valorPlano;
    private int duracaoPlano;
    private String descricaoPlano;

    private LocalDate dataInicio;
    private LocalDate dataFim;
}
