package com.example.demo.Vendas;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.demo.Produtos.Produto;
import com.example.demo.Produtos.ProdutoRepository;
import com.example.demo.Receitas.Receita;
import com.example.demo.Receitas.ReceitaRepository;
import com.example.demo.Users.User;
import com.example.demo.Users.UserRepository;


@Service
public class VendaService {

    @Autowired
    VendaRepository vendaRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ReceitaRepository receitaRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    public Venda realizarVenda(Venda venda) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        String login = userDetails.getUsername();

        User user = userRepository.findByLogin(login);

        venda.setDtVenda(LocalDate.now());
        venda.setUser(user);

        BigDecimal totalVenda = BigDecimal.ZERO;
        
        if (venda.getItens() != null) {
            for (ItemVenda item : venda.getItens()) {
                item.setVenda(venda);  

                BigDecimal totalItem = item.getPrecoUnitario()
                    .multiply(BigDecimal.valueOf(item.getQuantidade()));
                item.setTotal(totalItem);  

                totalVenda = totalVenda.add(totalItem); 

                Produto produto = item.getProduto();

                if (produto == null || produto.getId() == null) {
                    throw new RuntimeException("Produto não encontrado para o item da venda.");
                }
                produto = produtoRepository.findById(produto.getId())
                        .orElseThrow(() -> new RuntimeException("Produto não encontrado na base de dados."));

                if (produto.getEstoque() == null) {
                    produto.setEstoque(BigDecimal.ZERO);
                }

                BigDecimal novoEstoque = produto.getEstoque().subtract(BigDecimal.valueOf(item.getQuantidade()));

                if (novoEstoque.compareTo(BigDecimal.ZERO) < 0) {
                    throw new RuntimeException("Estoque insuficiente para o produto: " + produto.getNome());
                }

                produto.setEstoque(novoEstoque);
            
                produtoRepository.save(produto);
            }
        }
        
        venda.setVlTotal(totalVenda); 
        
        vendaRepository.save(venda);  

        Receita receita = new Receita();
        receita.setCodigo(venda.getCodigo());
        receita.setDtCadastro(LocalDate.now());
        receita.setDescricao("Receita referente a venda de código " + venda.getId());
        receita.setUserId(user);
        receita.setVlTotal(totalVenda);
        receita.setStBaixado(false);

        receitaRepository.save(receita);
        
        return venda;
    }

    public List<VendaResponseDTO> listarTodasAsVendas() {
      
        List<Venda> vendas = vendaRepository.findAll(); 

     
        return vendas.stream().map(venda -> new VendaResponseDTO(
            venda.getId(),
            venda.getCodigo(),
            venda.getDtVenda(),
            venda.getVlTotal(),
            venda.getCliente().getId(),
            venda.getCliente().getNome(),
            venda.getUser().getId(),
            venda.getUser().getUsername(),
         
            venda.getItens().stream()
                .map(item -> new ItemVendaDTO(
                    item.getProduto().getId(),
                    item.getProduto().getNome(),
                    item.getQuantidade(),
                    item.getPrecoUnitario(),
                    item.getTotal()
                ))
                .collect(Collectors.toList())
        )).toList();
    }
}
