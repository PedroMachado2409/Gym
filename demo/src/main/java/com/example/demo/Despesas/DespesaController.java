package com.example.demo.Despesas;

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
@RequestMapping("/api/despesas")
public class DespesaController {
    
    @Autowired
    private DespesaService despesaService;

    @GetMapping
    public List<DespesaResponseDTO> listarDespesas(){
        return despesaService.listarDespesas();
    }

    @PostMapping
    public ResponseEntity<?> cadastrarDespesa(@RequestBody @Valid DespesaRequestDTO dto){
        despesaService.SalvarDespesa(dto);
        return ResponseEntity.ok().body("Despesa Cadastrada com sucesso !");
    }

    @PutMapping("/baixar/{id}")
    public ResponseEntity<DespesaResponseDTO> baixarDespesa(@PathVariable Long id) {
        Despesa despesa = despesaService.baixarDespesa(id);

        DespesaResponseDTO responseDTO = new DespesaResponseDTO(
            despesa.getId(),
            despesa.getDescricao(),
            despesa.getVlTotal(),
            despesa.getUserId() != null ? despesa.getUserId().getId() : null,
            despesa.getStBaixado(),
            despesa.getDtCadastro()
        );

        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/removerBaixa/{id}")
    public ResponseEntity<DespesaResponseDTO> removerBaixa(@PathVariable Long id){
        Despesa despesa = despesaService.removerBaixa(id);

        DespesaResponseDTO responseDTO = new DespesaResponseDTO(
            despesa.getId(),
            despesa.getDescricao(),
            despesa.getVlTotal(),
            despesa.getUserId() != null ? despesa.getUserId().getId() : null,
            despesa.getStBaixado(),
            despesa.getDtCadastro()
        );
        
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletarDespesa(@PathVariable @Valid Long id){
        despesaService.deletarDespesa(id);
        return ResponseEntity.ok().body("Despesa Deletada com sucesso!");
    } 
    

}
