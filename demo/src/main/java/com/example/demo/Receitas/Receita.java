package com.example.demo.Receitas;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.example.demo.Users.User;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Receitas")
@Getter
@Setter
public class Receita {

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
