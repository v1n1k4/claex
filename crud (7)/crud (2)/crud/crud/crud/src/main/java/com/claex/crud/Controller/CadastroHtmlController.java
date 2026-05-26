package com.claex.crud.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.claex.crud.Entity.CadastroEntity;
import com.claex.crud.Service.CadastroService;

@Controller
@RequestMapping("/form_cadastro-html")
public class CadastroHtmlController {
  @Autowired
    private CadastroService service;

  // CREATE - salva no banco
    @PostMapping("/salvar")
    @ResponseBody
    public ResponseEntity<String> salvarCadastro(
        @RequestParam String nome,
        @RequestParam String email,
        @RequestParam String senha,
        @RequestParam(required = false, defaultValue = "aluno") String tipo
    ) {
        CadastroEntity cadastro = new CadastroEntity();
        cadastro.setNome(nome);
        cadastro.setEmail(email);
        cadastro.setSenha(senha);
        cadastro.setTipo(tipo);

        service.salvar(cadastro);
        return ResponseEntity.ok("Cadastro realizado com sucesso!");
    }

    // LIST - lista todos os cadastros
    @GetMapping("/listar")
    @ResponseBody
    public ResponseEntity<?> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    // GET BY ID - busca cadastro por id
    @GetMapping("/buscar/{id}")
    @ResponseBody
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.buscarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // UPDATE - atualiza cadastro existente
    @PutMapping("/atualizar/{id}")
    @ResponseBody
    public ResponseEntity<?> atualizar(
        @PathVariable Long id,
        @RequestParam String nome,
        @RequestParam String email,
        @RequestParam(required = false) String senha,
        @RequestParam(required = false) String tipo
    ) {
        try {
            CadastroEntity existente = service.buscarPorId(id);
            existente.setNome(nome);
            existente.setEmail(email);
            if (senha != null && !senha.isBlank()) {
                existente.setSenha(senha);
            }
            if (tipo != null && !tipo.isBlank()) {
                existente.setTipo(tipo);
            }
            service.salvar(existente);
            return ResponseEntity.ok("Cadastro atualizado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE - exclui cadastro
    @DeleteMapping("/deletar/{id}")
    @ResponseBody
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        try {
            service.deletar(id);
            return ResponseEntity.ok("Cadastro excluído com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
