package com.example.demo.FichaDeTreino;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.FichaDeTreino.FichaDeTreinoResponseDTO.ItemFichaTreinoResponseDTO;

import java.util.List;
import java.util.Optional;

@Service
public class FichaDeTreinoService {

    @Autowired
    private FichaDeTreinoRepository repository;


    public List<FichaDeTreinoResponseDTO> listarFichas(){
        List<FichaDeTreino> fichas = repository.findAll();

        return fichas.stream().map(fichass -> {
            FichaDeTreinoResponseDTO dto = new FichaDeTreinoResponseDTO();
            dto.setIdFicha(fichass.getId());
            dto.setNomeAluno(fichass.getAluno().getLogin());
            dto.setNomeProfessor(fichass.getProfessor().getLogin());
            dto.setTipoTreino(fichass.getTipoTreino());

            List<ItemFichaTreinoResponseDTO> equipamentos = fichass.getEquipamentos().stream().map(item -> {
                ItemFichaTreinoResponseDTO iDto = new ItemFichaTreinoResponseDTO();
                iDto.setNomeEquipamento(item.getEquipamento().getNome());
                iDto.setRepeticoes(item.getRepeticoes());
                return iDto;
            }).toList();
            dto.setEquipamentos(equipamentos);
            return dto;
        }).toList();

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
