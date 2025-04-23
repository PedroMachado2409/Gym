package com.example.demo.Clientes;

import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@Getter
public class ClienteResponseDTO {

    private Long id;
    private String nome;
    private String cpf;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dtNascimento;
    private String endereco;
    private String cep;
    private Long idUsuario;
    private LocalDate dtCadastro;

    public ClienteResponseDTO(Long id, String nome, String cpf, Date dtNascimento, String endereco, String cep,
            Long idUsuario, LocalDate dtCadastro) {

        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dtNascimento = dtNascimento;
        this.endereco = endereco;
        this.cep = cep;
        this.idUsuario = idUsuario;
        this.dtCadastro = dtCadastro;

    }

}
