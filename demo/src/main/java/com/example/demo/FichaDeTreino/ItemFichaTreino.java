package com.example.demo.FichaDeTreino;

import com.example.demo.Equipamento.Equipamento;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "ItemFichaTreino")
public class ItemFichaTreino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int repeticoes;

    @ManyToOne
    @JoinColumn(name = "ficha_id")
    private FichaDeTreino ficha;

    @ManyToOne
    @JoinColumn(name = "equipamento_id")
    private Equipamento equipamento;
}
