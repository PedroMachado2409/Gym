package com.example.demo.FichaDeTreino;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FichaDeTreinoRepository extends JpaRepository<FichaDeTreino, Long> {
}
