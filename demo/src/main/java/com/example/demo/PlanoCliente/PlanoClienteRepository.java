package com.example.demo.PlanoCliente;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Clientes.Cliente;

@Repository
public interface PlanoClienteRepository extends JpaRepository<PlanoCliente, Long> {

    Optional<PlanoCliente> findByCliente(Cliente cliente);
}
