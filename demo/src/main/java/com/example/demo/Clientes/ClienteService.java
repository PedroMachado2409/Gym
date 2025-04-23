package com.example.demo.Clientes;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Users.User;
import com.example.demo.Users.UserRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UserRepository userRepository;

    public Cliente cadastrarCliente(ClienteRequestDTO dto) {
        User user = userRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Cliente cliente = new Cliente();
        cliente.setUser(user);
        cliente.setNome(dto.getNome());
        cliente.setCpf(dto.getCpf());
        cliente.setDtNascimento(dto.getDtNascimento());
        cliente.setEndereco(dto.getEndereco());
        cliente.setCep(dto.getCep());
        cliente.setDtCadastro(LocalDate.now());

        return clienteRepository.save(cliente);
    }

    public List<ClienteResponseDTO> listarClientes() {
        return clienteRepository.findAll()
                .stream()
                .map(cliente -> new ClienteResponseDTO(
                        cliente.getId(),
                        cliente.getNome(),
                        cliente.getCpf(),
                        cliente.getDtNascimento(),
                        cliente.getEndereco(),
                        cliente.getCep(),
                        cliente.getUser() != null ? cliente.getUser().getId() : null,
                        cliente.getDtCadastro()))
                .collect(Collectors.toList());
    }

    public Optional<ClienteResponseDTO> buscarClientePorId(Long id) {
        return clienteRepository.findById(id)
                .map(cliente -> new ClienteResponseDTO(
                        cliente.getId(),
                        cliente.getNome(),
                        cliente.getCpf(),
                        cliente.getDtNascimento(),
                        cliente.getEndereco(),
                        cliente.getCep(),
                        cliente.getUser() != null ? cliente.getUser().getId() : null,
                        cliente.getDtCadastro()));
    }

    public List<ClienteResponseDTO> buscarPorNome(String nome) {
        List<Cliente> clientes = clienteRepository.findByNomeContainingIgnoreCase(nome);

        return clientes.stream()
                .map(cliente -> new ClienteResponseDTO(
                        cliente.getId(),
                        cliente.getNome(),
                        cliente.getCpf(),
                        cliente.getDtNascimento(),
                        cliente.getEndereco(),
                        cliente.getCep(),
                        cliente.getUser() != null ? cliente.getUser().getId() : null,
                        cliente.getDtCadastro()))
                .toList();
    }

    public List<ClienteResponseDTO> buscarPorCpf(String cpf) {
        List<Cliente> clientes = clienteRepository.findByCpfContainingIgnoreCase(cpf);
        return clientes.stream()
                .map(cliente -> new ClienteResponseDTO(
                        cliente.getId(),
                        cliente.getNome(),
                        cliente.getCpf(),
                        cliente.getDtNascimento(),
                        cliente.getEndereco(),
                        cliente.getCep(),
                        cliente.getUser() != null ? cliente.getUser().getId() : null,
                        cliente.getDtCadastro()))
                .toList();
    }

    
    public Cliente editarCliente(Long idCliente, ClienteRequestDTO dto) {
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        User user = userRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        cliente.setUser(user);
        cliente.setNome(dto.getNome());
        cliente.setCpf(dto.getCpf());
        cliente.setDtNascimento(dto.getDtNascimento());
        cliente.setEndereco(dto.getEndereco());
        cliente.setCep(dto.getCep());

        return clienteRepository.save(cliente);
    }

    public void DeletarCliente(Long id) {
        clienteRepository.deleteById(id);
    }

}
