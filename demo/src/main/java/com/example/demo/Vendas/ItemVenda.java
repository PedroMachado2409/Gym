package com.example.demo.Vendas;

import java.math.BigDecimal;

import com.example.demo.Produtos.Produto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "itemVenda")
public class ItemVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Produto produto;

    @ManyToOne
    private Venda venda;

    private Integer quantidade;
    private BigDecimal precoUnitario;
    private BigDecimal total;

    @PrePersist
    @PreUpdate
    public void calcularTotal() {
        if (quantidade != null && precoUnitario != null) {
            this.total = precoUnitario.multiply(BigDecimal.valueOf(quantidade));
        }
    }
}
