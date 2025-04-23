package com.example.demo.Movimentação_Saldo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;



import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Movimentacoes")
@Getter
@Setter
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo; 

    private BigDecimal valor;

    private UUID codigoOrigem = null;

    private LocalDate data;

}
