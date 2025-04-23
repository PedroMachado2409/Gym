package com.example.demo.PlanoCliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.demo.Clientes.Cliente;
import com.example.demo.Clientes.ClienteRepository;
import com.example.demo.Email.EmailService;
import com.example.demo.Plano.Plano;
import com.example.demo.Plano.PlanoRepository;
import com.example.demo.Receitas.Receita;
import com.example.demo.Receitas.ReceitaRepository;
import com.example.demo.Users.User;
import com.example.demo.Users.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PlanoClienteService {

    @Autowired
    private PlanoClienteRepository planoClienteRepository;

    @Autowired
    private PlanoRepository planoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ReceitaRepository receitaRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired EmailService emailService;

    public PlanoCliente criarPlanoCliente(PlanoClienteRequestDTO dto) {
        Plano plano = planoRepository.findById(dto.getPlanoId())
                .orElseThrow(() -> new RuntimeException("Plano não encontrado"));

        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Optional<PlanoCliente> planoExistente = planoClienteRepository.findByCliente(cliente);
        if (planoExistente.isPresent()) {
            throw new RuntimeException("Este cliente já possui um plano ativo.");
        }

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = userDetails.getUsername();
        User user = userRepository.findByLogin(login);

        PlanoCliente planoCliente = new PlanoCliente();
        planoCliente.setCliente(cliente);
        planoCliente.setPlano(plano);
        planoCliente.setDataInicio(LocalDate.now());
        planoCliente.setDataFim(LocalDate.now().plusDays(plano.getDuracao()));

        planoClienteRepository.save(planoCliente);

        Receita receita = new Receita();
        receita.setCodigo(planoCliente.getCodigo());
        receita.setDescricao("Receita gerada através do plano: " + plano.getNome() + " do Cliente: " + dto.getClienteId());
        receita.setVlTotal(plano.getValor());
        receita.setDtCadastro(LocalDate.now());
        receita.setUserId(user);
        receitaRepository.save(receita);

        try {
            emailService.enviarEmailDeNovoPlano(planoCliente);
        } catch (Exception e) {
            throw new RuntimeException("Plano vinculado ao cliente, porém não foi possível enviar o e-mail. Por favor, verifique as configurações de SMTP e a conexão de rede.", e); // Inclua a exceção original para melhor diagnóstico
        }

        return planoCliente;
    }

    public List<PlanoClienteResponseDTO> listarDTOs() {
        List<PlanoCliente> planosClientes = planoClienteRepository.findAll();

        return planosClientes.stream().map(planoCliente -> new PlanoClienteResponseDTO(
                planoCliente.getId(),
                planoCliente.getCliente().getNome(),
                planoCliente.getPlano().getNome(),
                planoCliente.getPlano().getValor(),
                planoCliente.getPlano().getDuracao(),
                planoCliente.getPlano().getDescricao(),
                planoCliente.getDataInicio(),
                planoCliente.getDataFim())).toList();
    }

    public Optional<PlanoCliente> buscarPorId(Long id) {
        return planoClienteRepository.findById(id);
    }

    public void deletarPorId(Long id) {
        
        PlanoCliente planoCliente = planoClienteRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Assinatura não encontrada ! "));

        Receita receita = receitaRepository.findByCodigo(planoCliente.getCodigo());
        

        if(Boolean.TRUE.equals(receita.getStBaixado())) {
            throw new RuntimeException("para excluir o plano, retire a baixa da Receita vinculada, receita de codigo: " + receita.getId());
        }
        planoClienteRepository.deleteById(id);
        receitaRepository.delete(receita);
        
    }

    public void reenviarEmailDeConfirmacao(Long id) {
        PlanoCliente plano = planoClienteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Plano não encontrado!"));
    
        try {
            emailService.enviarEmailDeNovoPlano(plano);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar e-mail. Verifique configurações de SMTP", e);
        }
    }

}
