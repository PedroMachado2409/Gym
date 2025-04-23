package com.example.demo.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Users.User;
import com.example.demo.Users.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository repository;
    @Autowired
    private TokenService tokenService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO data) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);

            var token = tokenService.generateToken((User) auth.getPrincipal());

            return ResponseEntity.ok(new LoginResponseDTO(token));
        } catch (AuthenticationException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Usuário ou senha inválidos");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO data) {
        if (this.repository.findByLogin(data.login()) != null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Usuário já existe");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());
        this.repository.save(newUser);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Usuário cadastrado com sucesso");
    }
    @GetMapping("/validar")
    public ResponseEntity<String> validateToken(@RequestHeader("Authorization") String authorizationHeader) {
        return ResponseEntity.ok("Usuário validado com sucesso");
    }
    
    @PutMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody @Valid UpdateUserDTO data, @RequestHeader("Authorization") String authorizationHeader) {
        // Extraímos o login do token de autorização
        String token = authorizationHeader.replace("Bearer ", "");
        String loggedUserLogin = tokenService.validateToken(token);

        if (loggedUserLogin == null || loggedUserLogin.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido");
        }
        // Buscamos o usuário autenticado no banco
        User existingUser = repository.findByLogin(loggedUserLogin);
        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
        // Verificar se a senha atual está correta
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(data.getSenhaAtual(), existingUser.getPassword())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Senha atual incorreta");
        }
        // Atualizar o login se necessário
        if (!data.getNovoLogin().isEmpty() && !data.getNovoLogin().equals(existingUser.getLogin())) {
            if (repository.findByLogin(data.getNovoLogin()) != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Login já existe");
            }
            existingUser.setLogin(data.getNovoLogin());
        }
        // Atualizar a senha
        if (!data.getNovaSenha().isEmpty()) {
            existingUser.setPassword(encoder.encode(data.getNovaSenha()));
        }
        repository.save(existingUser);
        return ResponseEntity.ok("Usuário atualizado com sucesso");
    }
}