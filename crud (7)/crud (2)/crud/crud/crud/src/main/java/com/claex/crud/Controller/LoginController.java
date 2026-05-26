package com.claex.crud.Controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.claex.crud.Entity.CadastroEntity;
import com.claex.crud.Repository.CadastroRepository;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private CadastroRepository repository;

    @PostMapping("/entrar")
    public ResponseEntity<Map<String, String>> login(
            @RequestParam String login,
            @RequestParam String senha) {

        // 1. Busca o usuário pelo nome no banco
        Optional<CadastroEntity> usuarioOpt = repository.findByNome(login);

        // 2. Usuário não encontrado
        if (usuarioOpt.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("erro", "Usuário não cadastrado!"));
        }

        CadastroEntity usuario = usuarioOpt.get();

        // 3. Senha incorreta
        if (!usuario.getSenha().equals(senha)) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("erro", "Senha incorreta!"));
        }

        // 4. Login OK — retorna dados do usuário
        return ResponseEntity.ok(Map.of(
                "mensagem", "Login realizado com sucesso!",
                "nome", usuario.getNome(),
                "tipo", usuario.getTipo()
        ));
    }
}
