package com.example.demo.Receitas;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.demo.Movimentação_Saldo.Movimentacao;
import com.example.demo.Movimentação_Saldo.MovimentacaoRepository;
import com.example.demo.Users.User;
import com.example.demo.Users.UserRepository;

@Service
public class ReceitaService {

    @Autowired
    ReceitaRepository receitaRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    MovimentacaoRepository movimentacaoRepository;

    public List<ReceitaResponseDTO> listarReceitas() {
        return receitaRepository.findAll()
                .stream()
                .map(receita -> new ReceitaResponseDTO(
                        receita.getId(),
                        receita.getDescricao(),
                        receita.getVlTotal(),
                        receita.getDtCadastro(),
                        receita.getStBaixado(),
                        receita.getUserId() != null ? receita.getUserId().getId() : null))
                .collect(Collectors.toList());
    }

    public Receita salvarReceita(ReceitaRequestDTO dto) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = userDetails.getUsername();
        User user = userRepository.findByLogin(login);

        Receita receita = new Receita();
        receita.setDescricao(dto.getDescricao());
        receita.setDtCadastro(LocalDate.now());
        receita.setUserId(user);
        receita.setVlTotal(dto.getVlTotal());
        return receitaRepository.save(receita);

    }

    public Receita baixarReceita(Long id) {
        Receita receita = receitaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receita não localizado"));

        if (Boolean.TRUE.equals(receita.getStBaixado())) {
            throw new RuntimeException("Receita ja esta baixada");
        }

        receita.setStBaixado(true);
        receitaRepository.save(receita);

        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setTipo("RECEITA");
        movimentacao.setValor(receita.getVlTotal());
        movimentacao.setData(LocalDate.now());
        movimentacao.setCodigoOrigem(receita.getCodigo());

        movimentacaoRepository.save(movimentacao);
        return receita;
    }

    public Receita removerBaixa(Long id) {
        Receita receita = receitaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receita não localizada"));

        if (Boolean.FALSE.equals(receita.getStBaixado())) {
            throw new RuntimeException("Receita não esta baixada");
        }

        receita.setStBaixado(false);
        receitaRepository.save(receita);

        Movimentacao movimentacao = movimentacaoRepository.findByCodigoOrigem(receita.getCodigo())
                .orElseThrow(() -> new RuntimeException("Movimentação não encontrada !"));

        movimentacaoRepository.delete(movimentacao);

        return receita;
    }

    public Receita atualizarReceita(Long id, ReceitaRequestDTO dto) {

        Receita receita = receitaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receita não encontrada"));

        if (Boolean.TRUE.equals(receita.getStBaixado())) {
            throw new RuntimeException("Não é permitido editar uma receita ja Baixada !");
        }

        receita.setDescricao(dto.getDescricao());
        receita.setVlTotal(dto.getVlTotal());
        return receitaRepository.save(receita);
    }

    public void deletarReceita(Long id){
        Receita receita = receitaRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Receita não encontrada"));

        if (Boolean.TRUE.equals(receita.getStBaixado())) {
            throw new RuntimeException("Não é permitido excluir uma receita ja Baixada !");
        }

        receitaRepository.deleteById(id);
    }

}
