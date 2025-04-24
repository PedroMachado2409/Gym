package com.example.demo.Clientes;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteRequestDTO {

    @NotNull(message = "Usuario é obrigatório")
    private Long idUsuario;

    @NotNull(message = "nome é obrigatorio")
    private String nome;
    @NotNull(message = "cpf é obrigatorio")
    private String cpf;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "Data de nascimento é obrigatório")
    private Date dtNascimento;

    @NotBlank(message = "endereco é obrigatorio")
    private String endereco;

    @NotBlank(message = "cep é obrigatorio")
    private String cep;

    @NotBlank(message = "Email é obrigatório")
    private String email;
    
}
