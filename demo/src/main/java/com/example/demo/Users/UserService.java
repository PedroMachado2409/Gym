package com.example.demo.Users;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponseDTO> listarTodosUsuarios() {
        return userRepository.findAll()
                .stream()
                .map(user -> new UserResponseDTO(user.getId(), user.getLogin(), user.getRole()))
                .collect(Collectors.toList());
    }

    public UserResponseDTO buscarPorId(Long id) {
        return userRepository.findById(id)
                .map(user -> new UserResponseDTO(user.getId(), user.getLogin(), user.getRole()))
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + id));
    }

}
