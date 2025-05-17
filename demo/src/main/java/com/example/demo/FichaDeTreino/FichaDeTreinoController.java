package com.example.demo.FichaDeTreino;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/fichas")
public class FichaDeTreinoController {

    @Autowired
    private FichaDeTreinoService service;

    @GetMapping
    public List<FichaDeTreinoResponseDTO> listarTodas() {
        return service.listarFichas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FichaDeTreinoResponseDTO> buscarPorId(@PathVariable Long id) {
        Optional<FichaDeTreino> ficha = service.buscarPorId(id);
        return ficha.map(f -> ResponseEntity.ok(convertToDTO(f)))
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public FichaDeTreino criarFicha(@RequestBody FichaDeTreino ficha) {
        // Validando se o tipo de treino está correto
        if (ficha.getTipoTreino() == null || !ficha.getTipoTreino().matches("[A-F]")) {
            throw new IllegalArgumentException("Tipo de treino inválido. Deve ser A, B, C, D, E ou F.");
        }
        return service.salvar(ficha);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFicha(@PathVariable Long id) {
        if (service.buscarPorId(id).isPresent()) {
            service.deletar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private FichaDeTreinoResponseDTO convertToDTO(FichaDeTreino ficha) {
        FichaDeTreinoResponseDTO dto = new FichaDeTreinoResponseDTO();
        dto.setIdFicha(ficha.getId()); 
        dto.setNomeAluno(ficha.getAluno().getLogin());
        dto.setNomeProfessor(ficha.getProfessor().getLogin());
        dto.setTipoTreino(ficha.getTipoTreino()); 
        dto.setEquipamentos(ficha.getEquipamentos().stream()
            .map(item -> {
                FichaDeTreinoResponseDTO.ItemFichaTreinoResponseDTO itemDTO = new FichaDeTreinoResponseDTO.ItemFichaTreinoResponseDTO();
                itemDTO.setNomeEquipamento(item.getEquipamento().getNome());
                itemDTO.setRepeticoes(item.getRepeticoes());
                return itemDTO;
            })
            .collect(Collectors.toList()));
        return dto;
    }
}
