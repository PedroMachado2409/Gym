package com.example.demo.Equipamento;

import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "equipamento")
public class Equipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private BigDecimal carga;

    private LocalDate dtCadastro;

    private LocalDate ultimaManutencao;

    private LocalDate proximaManutencao;
}
