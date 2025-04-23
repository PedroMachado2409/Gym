package com.example.demo.Plano;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanoService {

    @Autowired
    private PlanoRepository planoRepository;


    public List<Plano> listarTodos() {
        return planoRepository.findAll();
    }

    public Optional<Plano> buscarPorId(Long id) {
        return planoRepository.findById(id);
    }

    public List<Plano> buscarPlanoPorDescricao(String descricao){
        return planoRepository.findByDescricaoContainingIgnoreCase(descricao);
    }

    public Plano salvar(PlanoRequestDTO dto) {
        Plano plano = new Plano();
        plano.setDescricao(dto.getDescricao());
        plano.setNome(dto.getNome());
        plano.setDuracao(dto.getDuracao());
        plano.setValor(dto.getValor());

        return planoRepository.save(plano);
        
    }

    public Plano editarPlano(Long id, PlanoRequestDTO dto){
        Plano plano = planoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Plano Não encontrado"));

        plano.setDescricao(dto.getDescricao());
        plano.setNome(dto.getNome());
        plano.setDuracao(dto.getDuracao());
        plano.setValor(dto.getValor());

        return planoRepository.save(plano);

    }
  

    public void deletar(Long id) {
        planoRepository.deleteById(id);
    }
}
