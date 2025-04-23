package com.example.demo.Security;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserDTO {
    @NotBlank
    private String senhaAtual;
    
    @NotBlank
    private String novaSenha;
    
    private String novoLogin;
}
