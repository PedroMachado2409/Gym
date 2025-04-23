package com.example.demo.Vendas;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import lombok.Getter;

@Getter
public class VendaResponseDTO {

    private Long id;
    private UUID codigo;
    private LocalDate dtVenda;
    private BigDecimal vlTotal;
    private Long clienteId; 
    private String clienteNome;  
    private Long userId;        
    private String userNome;    
    private List<ItemVendaDTO> itens;

    public VendaResponseDTO(
        Long id,
        UUID codigo,
        LocalDate dtVenda,
        BigDecimal vlTotal,
        Long clienteId,
        String clienteNome,
        Long userId,
        String userNome,
        List<ItemVendaDTO> itens
    ) {
        this.id = id;
        this.codigo = codigo;
        this.dtVenda = dtVenda;
        this.vlTotal = vlTotal;
        this.clienteId = clienteId;
        this.clienteNome = clienteNome;
        this.userId = userId;
        this.userNome = userNome;
        this.itens = itens;
    }
}
