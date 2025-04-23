package com.example.demo.Produtos;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    public Produto cadastrarProduto(ProdutoRequestDTO dto) {
        
        Produto produto = new Produto();
        produto.setDtCadastro(LocalDate.now());
        produto.setNome(dto.getNome());
        produto.setEstoque(dto.getEstoque());
        produto.setUnidade(dto.getUnidade());
        return produtoRepository.save(produto);

    }

    public List<Produto> listarProdutos(){
        return produtoRepository.findAll();
    }

    public List<Produto> BuscarProdutoPorNome(String nome){
        return produtoRepository.findByNomeContainingIgnoreCase(nome);
    }

    public Optional<Produto> BuscarProdutoPorId(Long id){
        return produtoRepository.findById(id);
    }

    public Produto atualizarProduto(Long id, ProdutoRequestDTO dto){
        Produto produto = produtoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Produto não localizado"));

        produto.setNome(dto.getNome());
        produto.setEstoque(dto.getEstoque());
        produto.setUnidade(dto.getUnidade());
        return produtoRepository.save(produto);
    }

    public void deletarProduto(Long id){
        produtoRepository.deleteById(id);
    }

}
