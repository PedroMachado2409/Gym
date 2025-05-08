package com.example.demo.Equipamento;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EquipamentoService {

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    
    public Equipamento salvarEquipamento(EquipamentoRequestDTO dto) {

        if (Objects.isNull(dto.getNome())) {
            throw new IllegalArgumentException("O nome do equipamento é obrigatório.");
        }
        if (Objects.isNull(dto.getCarga())) {
            throw new IllegalArgumentException("A carga do equipamento é obrigatória.");
        }
        if (Objects.isNull(dto.getUltimaManutencao())) {
            throw new IllegalArgumentException("A data da última manutenção é obrigatória.");
        }
        
        Equipamento equipamento = new Equipamento();
        equipamento.setCarga(dto.getCarga());
        equipamento.setDtCadastro(LocalDate.now());
        equipamento.setNome(dto.getNome());
        equipamento.setProximaManutencao(dto.getProximaManutencao());
        equipamento.setUltimaManutencao(dto.getUltimaManutencao());

        return equipamentoRepository.save(equipamento);

    }

    public List<Equipamento> buscarTodosEquipamentos() {
        return equipamentoRepository.findAll();
    }

    public List<Equipamento> buscarEquipamentoPorNome(String nome){
        return equipamentoRepository.findByNomeContainingIgnoreCase(nome);

    }

    public Optional<Equipamento> buscarEquipamentoPorId(Long id) {
        return equipamentoRepository.findById(id);
    }

    public Equipamento editarEquipamento(Long id, Equipamento equipamento){
        Equipamento equipamentoAtualizado = equipamentoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Equipamento não localizado"));
    
        equipamentoAtualizado.setCarga(equipamento.getCarga());
        equipamentoAtualizado.setNome(equipamento.getNome());
        equipamentoAtualizado.setProximaManutencao(equipamento.getProximaManutencao());
        equipamentoAtualizado.setUltimaManutencao(equipamento.getUltimaManutencao());
    
        return equipamentoRepository.save(equipamentoAtualizado);
    }

   
   

    public void deletarEquipamento(Long id) {
        equipamentoRepository.deleteById(id);
    }


  
}
