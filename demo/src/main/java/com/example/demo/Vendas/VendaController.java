package com.example.demo.Vendas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vendas")
public class VendaController {
    
    @Autowired
    VendaService vendaService;

    @GetMapping
    public ResponseEntity<List<VendaResponseDTO>> listarVendasComItens() {
        List<VendaResponseDTO> vendas = vendaService.listarTodasAsVendas();
        return ResponseEntity.ok(vendas);
    }

    @PostMapping
    public ResponseEntity<String> cadastrarVenda(@RequestBody Venda venda) {
        Venda novaVenda = vendaService.realizarVenda(venda);
        return ResponseEntity.ok("Venda realizada com sucesso! Código da venda: " + novaVenda.getId());
    }
    
}
