package com.example.demo.Movimentação_Saldo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/movimentacao")
public class MovimentacaoController {

    @Autowired
    MovimentacaoService movimentacaoService;

    @GetMapping
    public List<MovimentacaoResponseDTO> listarMovimentacoes(){
       
        return movimentacaoService.listarMovimentacoes();
    } 

    @GetMapping("/saldo")
    public SaldoDTO obterSaldo() {
        return movimentacaoService.calcularSaldo();
    }

}
