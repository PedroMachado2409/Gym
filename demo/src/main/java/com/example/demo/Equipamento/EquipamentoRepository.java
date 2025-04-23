package com.example.demo.Equipamento;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;



public interface EquipamentoRepository extends JpaRepository<Equipamento, Long>,JpaSpecificationExecutor<Equipamento> {
    List<Equipamento> findByNomeContainingIgnoreCase(String nome);
}
