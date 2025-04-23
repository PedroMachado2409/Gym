package com.example.demo.FichaDeTreino;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FichaDeTreinoService {

    @Autowired
    private FichaDeTreinoRepository repository;

    public List<FichaDeTreino> listarTodas() {
        return repository.findAll();
    }

    public Optional<FichaDeTreino> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public FichaDeTreino salvar(FichaDeTreino ficha) {
        return repository.save(ficha);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
