import com.example.demo.Clientes.Cliente;
import com.example.demo.Clientes.ClienteRepository;
import com.example.demo.Clientes.ClienteRequestDTO;
import com.example.demo.Clientes.ClienteResponseDTO;
import com.example.demo.Clientes.ClienteService;
import com.example.demo.PlanoCliente.PlanoClienteRepository;
import com.example.demo.Users.User;
import com.example.demo.Users.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = com.example.demo.DemoApplication.class)
public class ClienteServiceTest {

    @Autowired
    private ClienteService clienteService;

    @MockBean
    private ClienteRepository clienteRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PlanoClienteRepository planoClienteRepository;

    @Test
    void deveCadastrarClienteComSucesso() {
        
        ClienteRequestDTO dto = new ClienteRequestDTO();
        dto.setNome("João Silva");
        dto.setCpf("12345678900");
        dto.setCep("12345-678");
        dto.setEndereco("Rua Exemplo, 123");
        dto.setEmail("joao@email.com");
        dto.setIdUsuario(1L);
        dto.setDtNascimento(new Date());

        User user = new User();
        user.setId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(clienteRepository.save(any(Cliente.class))).thenAnswer(invocation -> invocation.getArgument(0));

      
        Cliente cliente = clienteService.cadastrarCliente(dto);

       
        assertNotNull(cliente);
        assertEquals("João Silva", cliente.getNome());
        assertEquals("12345678900", cliente.getCpf());
        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }

    @Test
    void deveListarClientes() {
        // Arrange
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Ana");
        cliente.setCpf("98765432100");
        cliente.setDtNascimento(new Date());
        cliente.setEndereco("Rua B");
        cliente.setCep("99999-000");
        cliente.setDtCadastro(LocalDate.now());
        cliente.setEmail("ana@email.com");

        when(clienteRepository.findAll()).thenReturn(List.of(cliente));

   
        List<ClienteResponseDTO> clientes = clienteService.listarClientes();

        assertEquals(1, clientes.size());
        assertEquals("Ana", clientes.get(0).getNome());
    }

    @Test
    void deveBuscarClientePorId() {
  
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Carlos");

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

       
        Optional<ClienteResponseDTO> resultado = clienteService.buscarClientePorId(1L);

  
        assertTrue(resultado.isPresent());
        assertEquals("Carlos", resultado.get().getNome());
    }

    @Test
    void deveExcluirClienteSemPlano() {
     
        Cliente cliente = new Cliente();
        cliente.setId(1L);

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(planoClienteRepository.findByCliente_Id(1L)).thenReturn(Optional.empty());

        clienteService.DeletarCliente(1L);

     
        verify(clienteRepository, times(1)).deleteById(1L);
    }

    @Test
    void naoDeveExcluirClienteComPlanoAtivo() {
        
        Cliente cliente = new Cliente();
        cliente.setId(1L);

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(planoClienteRepository.findByCliente_Id(1L)).thenReturn(Optional.of(mock(com.example.demo.PlanoCliente.PlanoCliente.class)));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> clienteService.DeletarCliente(1L));
        assertEquals("Não é possivel deletar o cliente, pois o mesmo tem um plano ativo", exception.getMessage());
    }
}