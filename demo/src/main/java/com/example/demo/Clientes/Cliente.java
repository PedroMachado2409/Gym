package com.example.demo.Clientes;

import java.time.LocalDate;
import java.util.Date;

import com.example.demo.Users.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@Table(name = "Clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;
    private String nome;
    private String cpf;


    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dtNascimento;
    
    private String endereco;
    private String cep;
    private LocalDate dtCadastro;
    private String email;

}
