package com.example.demo.PlanoCliente;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class PlanoClienteRequestDTO {
    private Long id;

    @NotNull(message = "é necessario informar o cliente")
    private Long clienteId;

    @NotNull(message = "É necessario informar o Plano")
    private Long planoId;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private UUID codigo = UUID.randomUUID();

}
