package com.example.demo.Plano;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/planos")
public class PlanoController {

    @Autowired
    private PlanoService planoService;

    @GetMapping
    public List<Plano> listarTodos() {
        return planoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plano> buscarPorId(@PathVariable Long id) {
        Optional<Plano> plano = planoService.buscarPorId(id);
        return plano.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody @Valid PlanoRequestDTO dto) {
             planoService.salvar(dto);
             return ResponseEntity.ok().body("Plano Cadastrado com sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?>editarPlano(@PathVariable Long id,@Valid @RequestBody PlanoRequestDTO dto ){
         planoService.editarPlano(id, dto);
         return ResponseEntity.ok().body("Plano Atualizado com sucesso ");

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        planoService.deletar(id);
        return ResponseEntity.ok().body("Plano deletado com sucesso !");
    }
}

