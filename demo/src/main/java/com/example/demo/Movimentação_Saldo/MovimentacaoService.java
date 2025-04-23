package com.example.demo.Movimentação_Saldo;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovimentacaoService {
    
    @Autowired
    MovimentacaoRepository movimentacaoRepository;


    public List<MovimentacaoResponseDTO> listarMovimentacoes(){
        return movimentacaoRepository.findAll()
        .stream()
        .map(movimentacao -> new MovimentacaoResponseDTO(
            movimentacao.getId(),
            movimentacao.getTipo(),
            movimentacao.getValor(),
            movimentacao.getData()
            ))
            .collect(Collectors.toList());
    } 

    public SaldoDTO calcularSaldo() {
        List<Movimentacao> receitas = movimentacaoRepository.findAllByTipo("RECEITA");
        List<Movimentacao> despesas = movimentacaoRepository.findAllByTipo("DESPESA");

        BigDecimal totalReceitas = receitas.stream()
                .map(Movimentacao::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalDespesas = despesas.stream()
                .map(Movimentacao::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new SaldoDTO(totalReceitas, totalDespesas);
    }
}
