package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.Equipamento.Equipamento;
import com.example.demo.Equipamento.EquipamentoRepository;
import com.example.demo.Equipamento.EquipamentoRequestDTO;
import com.example.demo.Equipamento.EquipamentoService;



@SpringBootTest(classes = com.example.demo.DemoApplication.class)
public class EquipamentoServiceTest {
    
    @Autowired
    private EquipamentoService equipamentoService;

    @MockBean
    private EquipamentoRepository equipamentoRepository;


    @Test
    void deveCadastrarEquipamentoComSucesso() {
        
        EquipamentoRequestDTO dto = new EquipamentoRequestDTO();
        dto.setNome("Equipamento Teste");
        dto.setCarga(new BigDecimal("100.50"));
        dto.setDtCadastro(LocalDate.now());
        dto.setProximaManutencao(LocalDate.now().plusMonths(6));
        dto.setUltimaManutencao(LocalDate.now().minusMonths(1));

        Equipamento equipamentoSalvo = new Equipamento();
        equipamentoSalvo.setId(1L); 
        equipamentoSalvo.setNome(dto.getNome());
        equipamentoSalvo.setCarga(dto.getCarga());
        equipamentoSalvo.setDtCadastro(dto.getDtCadastro());
        equipamentoSalvo.setProximaManutencao(dto.getProximaManutencao());
        equipamentoSalvo.setUltimaManutencao(dto.getUltimaManutencao());
      
        when(equipamentoRepository.save(any(Equipamento.class))).thenReturn(equipamentoSalvo);

        Equipamento equipamentoRetornado = equipamentoService.salvarEquipamento(dto);

        assertNotNull(equipamentoRetornado);
        assertEquals(equipamentoSalvo.getId(), equipamentoRetornado.getId());
        assertEquals(dto.getNome(), equipamentoRetornado.getNome());
        assertEquals(dto.getCarga(), equipamentoRetornado.getCarga());
        assertEquals(dto.getProximaManutencao(), equipamentoRetornado.getProximaManutencao());
        assertEquals(dto.getUltimaManutencao(), equipamentoRetornado.getUltimaManutencao());
        assertNotNull(equipamentoRetornado.getDtCadastro()); 

        verify(equipamentoRepository, times(1)).save(any(Equipamento.class));
    }
    

}
