package com.example.demo.Receitas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/receitas")
public class ReceitaController {
    

    @Autowired
    private ReceitaService receitaService;

    @PostMapping
    public ResponseEntity<?> cadastrarReceita(@RequestBody @Valid ReceitaRequestDTO dto){
        receitaService.salvarReceita(dto);
        return ResponseEntity.ok().body("Receita Cadastrada com sucesso");
    }
    

    @GetMapping
    public List<ReceitaResponseDTO> listarReceitas (){
        return receitaService.listarReceitas();
    }



    @PutMapping("/baixar/{id}")
    public ResponseEntity<ReceitaResponseDTO> baixarReceita(@PathVariable Long id){
        Receita receita = receitaService.baixarReceita(id);

        ReceitaResponseDTO responseDTO = new ReceitaResponseDTO(
            receita.getId(), 
            receita.getDescricao(),
            receita.getVlTotal(),
            receita.getDtCadastro(),
            receita.getStBaixado(),
            receita.getUserId()  != null ? receita.getUserId().getId() : null
        );

        return ResponseEntity.ok(responseDTO);

    }

    @PutMapping("/removerBaixa/{id}")
    public ResponseEntity<ReceitaResponseDTO> removerBaixa(@PathVariable Long id){

        Receita receita = receitaService.removerBaixa(id);

        ReceitaResponseDTO responseDTO = new ReceitaResponseDTO(
            receita.getId(), 
            receita.getDescricao(),
            receita.getVlTotal(),
            receita.getDtCadastro(),
            receita.getStBaixado(),
            receita.getUserId()  != null ? receita.getUserId().getId() : null
        );

        return ResponseEntity.ok(responseDTO);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarReceita(@PathVariable Long id, @RequestBody ReceitaRequestDTO dto){
        receitaService.atualizarReceita(id, dto);
        return ResponseEntity.ok().body("Receita Atualizada com sucesso !");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarReceita(@PathVariable Long id){
        receitaService.deletarReceita(id);
        return ResponseEntity.ok().body("Receita deletada com sucesso !");
    }

}
