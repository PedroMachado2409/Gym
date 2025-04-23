package com.example.demo.Produtos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    
    @Autowired
    ProdutoService produtoService;

    @GetMapping
    public List<Produto> listarProdutos(){
        return produtoService.listarProdutos();
    }

    @GetMapping("{id}")
    public Optional<Produto> buscarProdutoPorId(@PathVariable Long id){
        return produtoService.BuscarProdutoPorId(id);
    }

    @GetMapping("buscarNome")
    public List<Produto> buscarProdutoPorNome(@RequestParam String nome){
        return produtoService.BuscarProdutoPorNome(nome);
    }

    @PostMapping
    public ResponseEntity<?> cadastrarProduto(@RequestBody @Valid ProdutoRequestDTO dto){
        produtoService.cadastrarProduto(dto);
        return ResponseEntity.ok().body("Produto Cadastrado com sucesso !");
    }

    @PutMapping("{id}")
    public ResponseEntity<?> editarProduto(@PathVariable Long id, @RequestBody @Valid ProdutoRequestDTO dto){
        produtoService.atualizarProduto(id, dto);
        return ResponseEntity.ok().body("produto Atualizado com Sucesso !");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletarProduto(@PathVariable Long id){
        produtoService.deletarProduto(id);
        return ResponseEntity.ok().body("Produto Deletado com Sucesso !");
    }

}
