package com.example.demo.Despesas;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.example.demo.Users.User;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Despesas")
@Getter
@Setter
public class Despesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID codigo = UUID.randomUUID();
    private String descricao;
    private BigDecimal vlTotal;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;
    private Boolean stBaixado = false;
    private LocalDate dtCadastro;
}
