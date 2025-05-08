package com.example.demo.FichaDeTreino;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FichaDeTreinoRepository extends JpaRepository<FichaDeTreino, Long> {


}
