package com.example.demo.Movimentação_Saldo;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;




@Getter
@Setter
public class SaldoDTO {
    private BigDecimal receitas;
    private BigDecimal despesas;
    private BigDecimal saldo;

    public SaldoDTO(BigDecimal receitas, BigDecimal despesas) {
        this.receitas = receitas;
        this.despesas = despesas;
        this.saldo = receitas.add(despesas);
    }

}
