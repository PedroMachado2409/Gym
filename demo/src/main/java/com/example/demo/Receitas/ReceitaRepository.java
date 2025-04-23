package com.example.demo.Receitas;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;


public interface ReceitaRepository extends JpaRepository<Receita, Long >{

    Receita findByCodigo(UUID codigo);
    
} 
