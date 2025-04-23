package com.example.demo.FichaDeTreino;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.Users.User;

@Entity
@Getter
@Setter
public class FichaDeTreino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User aluno;

    @ManyToOne
    private User professor;

    @OneToMany
    private List<ItemFichaTreino> equipamentos;

    private LocalDate dataInicio;
    private LocalDate dataFim;
    
    private String tipoTreino;  

   
}
