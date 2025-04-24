package com.example.demo.Clientes;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<ClienteResponseDTO> buscarClientes() {
        return clienteService.listarClientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarClientePorId(@PathVariable Long id) {
        return clienteService.buscarClientePorId(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Cliente com ID " + id + " não encontrado."));
    }

    @GetMapping("/buscarNome")
    public ResponseEntity<List<ClienteResponseDTO>> buscarPorNome(@RequestParam String nome) {
        List<ClienteResponseDTO> clientes = clienteService.buscarPorNome(nome);
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/buscarCpf")
    public ResponseEntity<List<ClienteResponseDTO>> buscarPorTrechoCpf(@RequestParam String cpf) {
        List<ClienteResponseDTO> clientes = clienteService.buscarPorCpf(cpf);
        return ResponseEntity.ok(clientes);
    }

    @PostMapping
    public ResponseEntity<?> cadastrarCliente(@Valid @RequestBody ClienteRequestDTO dto) {
        clienteService.cadastrarCliente(dto);
        return ResponseEntity.ok().body("Cliente cadastrado com sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarCliente( @PathVariable Long id, @RequestBody @Valid ClienteRequestDTO dto) {
        clienteService.editarCliente(id, dto);
        return ResponseEntity.ok().body("Cliente atualizado com sucesso !");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarCliente(@PathVariable @Valid Long id) {
        clienteService.DeletarCliente(id);
        return ResponseEntity.ok("Cliente deletado com sucesso.");

    }

    

}
