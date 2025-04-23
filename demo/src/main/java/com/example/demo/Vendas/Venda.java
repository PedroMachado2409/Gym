package com.example.demo.Vendas;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.example.demo.Clientes.Cliente;
import com.example.demo.Users.User;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "vendas")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ItemVenda> itens;

    @ManyToOne
    private Cliente cliente;

    private LocalDate dtVenda;

    private UUID codigo = UUID.randomUUID();

    private BigDecimal vlTotal;

    @ManyToOne
    private User user;

}
