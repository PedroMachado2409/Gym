package com.example.demo.Produtos;

import java.math.BigDecimal;
import java.time.LocalDate;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Produtos")
public class Produto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private BigDecimal estoque = BigDecimal.ZERO; 
    private LocalDate dtCadastro;
    private String unidade;

}

