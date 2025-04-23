package com.example.demo.Movimentação_Saldo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;



public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
    Optional<Movimentacao> findByCodigoOrigem(UUID codigoReferencia);

    List<Movimentacao> findAllByTipo(String tipo);
}
