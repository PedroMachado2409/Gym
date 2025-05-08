package com.example.demo.PlanoCliente;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/planoClientes")
public class PlanoClienteController {

    @Autowired
    private PlanoClienteService planoClienteService;

    @PostMapping
    public ResponseEntity<?> cadastrarPlanoCliente(@RequestBody @Valid PlanoClienteRequestDTO dto){
        planoClienteService.criarPlanoCliente(dto);
        return ResponseEntity.ok().body("Plano vinculado ao cliente !");
    }

    @GetMapping
    public List<PlanoClienteResponseDTO> listarResumo() {
        return planoClienteService.listarDTOs();
    }

    @GetMapping("/{id}")
    public PlanoClienteResponseDTO buscarPorId(@PathVariable Long id) {
        return planoClienteService.buscarPorId(id);
           
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarPorId(@PathVariable Long id) {
        planoClienteService.deletarPorId(id);
        return ResponseEntity.ok().body("Assinatura excluida com sucesso !");
    }

}
