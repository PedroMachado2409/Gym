package com.example.demo.FichaDeTreino;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FichaDeTreinoResponseDTO {

    private Long idFicha; 
    private String nomeAluno;
    private String nomeProfessor;
    private List<ItemFichaTreinoResponseDTO> equipamentos;
    private String tipoTreino; 

    

    @Getter
    @Setter
    public static class ItemFichaTreinoResponseDTO {
        private String nomeEquipamento;
        private int repeticoes;   
    }
}
