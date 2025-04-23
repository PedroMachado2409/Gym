package com.example.demo.Despesas;

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
public class DespesaService {

    @Autowired
    DespesaRepository despesaRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MovimentacaoRepository movimentacaoRepository;

    public Despesa SalvarDespesa(DespesaRequestDTO dto) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        String login = userDetails.getUsername();
        User user = userRepository.findByLogin(login);

        Despesa despesa = new Despesa();
        despesa.setDescricao(dto.getDescricao());
        despesa.setUserId(user);
        despesa.setStBaixado(false);
        despesa.setVlTotal(dto.getVlTotal());
        despesa.setCodigo(dto.getCodigo());
        despesa.setDtCadastro(LocalDate.now());

        if (despesa.getVlTotal() != null && despesa.getVlTotal().signum() > 0) {
            despesa.setVlTotal(despesa.getVlTotal().negate());
        }
        return despesaRepository.save(despesa);

    }

    public List<DespesaResponseDTO> listarDespesas() {
        return despesaRepository.findAll()
                .stream()
                .map(despesa -> new DespesaResponseDTO(
                        despesa.getId(),
                        despesa.getDescricao(),
                        despesa.getVlTotal(),
                        despesa.getUserId() != null ? despesa.getUserId().getId() : null,
                        despesa.getStBaixado(),
                        despesa.getDtCadastro()))
                .collect(Collectors.toList());

    }

    public Despesa baixarDespesa(Long id) {
        Despesa despesa = despesaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Despesa não encontrada"));

        if (Boolean.TRUE.equals(despesa.getStBaixado())) {
            throw new RuntimeException("Despesa já está baixada");
        }

        despesa.setStBaixado(true);
        despesaRepository.save(despesa);

        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setTipo("DESPESA");
        movimentacao.setData(LocalDate.now());
        movimentacao.setValor(despesa.getVlTotal());
        movimentacao.setCodigoOrigem(despesa.getCodigo());
        movimentacaoRepository.save(movimentacao);
        return despesa;
    }

    public Despesa removerBaixa(Long id) {

        Despesa despesa = despesaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Despesa nao localizada com o id" + id));
        if (Boolean.FALSE.equals(despesa.getStBaixado())) {
            throw new RuntimeException("Despesa do id" + id + "não esta baixada ");
        }
        despesa.setStBaixado(false);
        despesaRepository.save(despesa);

        Movimentacao movimentacao = movimentacaoRepository.findByCodigoOrigem(despesa.getCodigo())
                .orElseThrow(() -> new RuntimeException("Movimentação não foi excluida"));
        movimentacaoRepository.delete(movimentacao);
        return despesa;
    }

    public Despesa atualizarDespesa(Long id, DespesaRequestDTO dto) {
        Despesa despesa = despesaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Despesa não encotrada"));

        despesa.setDescricao(dto.getDescricao());
        despesa.setUserId(dto.getUserId());
        despesa.setStBaixado(false);
        despesa.setVlTotal(dto.getVlTotal());
        despesa.setCodigo(dto.getCodigo());
        return despesaRepository.save(despesa);
    }

    public void deletarDespesa(Long id) {
        Despesa despesa = despesaRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Despesa não encotrada"));
        
        if(Boolean.TRUE.equals(despesa.getStBaixado())) {
            throw new RuntimeException("Não é possivel excluir uma despesa baixada, remova a baixa para excluir !");
        }

        despesaRepository.deleteById(id);
    }

}
