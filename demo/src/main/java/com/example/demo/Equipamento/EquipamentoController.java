package com.example.demo.Equipamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/equipamentos")
public class EquipamentoController {

    @Autowired
    private EquipamentoService equipamentoService;

    @PostMapping
    public ResponseEntity<Equipamento> cadastrarEquipamamento(@Valid @RequestBody EquipamentoRequestDTO dto){
        Equipamento equipamento = equipamentoService.salvarEquipamento(dto);
        return ResponseEntity.ok(equipamento);
    }

    @GetMapping
    public List<Equipamento> listarEquipamentos(){
        return equipamentoService.buscarTodosEquipamentos();
    }

    @GetMapping("/buscar/nome")
    public List<Equipamento>listarEquipamentosPorNome (@RequestParam String nome) {
        return equipamentoService.buscarEquipamentoPorNome(nome);
    }

    @GetMapping("/buscar/{id}")
    public Optional<Equipamento> buscarEquipamentoPorId(@PathVariable Long id) {
        return equipamentoService.buscarEquipamentoPorId(id);
    }

    @PutMapping("/{id}")
    public Equipamento editarEquipamento(@PathVariable Long id, @RequestBody Equipamento equipamento){
        return equipamentoService.editarEquipamento(id, equipamento);
    }

    @DeleteMapping("/{id}")
    public void deletarEquipamento(@PathVariable Long id) {
        equipamentoService.deletarEquipamento(id);
    }


}
