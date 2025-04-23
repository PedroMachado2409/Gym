package com.example.demo.PlanoCliente;

import com.example.demo.Clientes.Cliente;
import com.example.demo.Plano.Plano;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "plano_cliente")
public class PlanoCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToOne
    @JoinColumn(name = "plano_id")
    private Plano plano;

    private UUID codigo = UUID.randomUUID();

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataInicio;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFim;

    @PrePersist
    public void calcularDatas() {
        if (dataInicio == null) {
            dataInicio = LocalDate.now();
        }
        if (plano != null && dataFim == null) {
            dataFim = dataInicio.plusDays(plano.getDuracao());
        }
    }
}
