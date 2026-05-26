package com.claex.crud.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.claex.crud.Entity.CadastroEntity;
import com.claex.crud.Repository.CadastroRepository;
import java.util.List;
@Service
public class CadastroService {
@Autowired
private CadastroRepository repository;

// LISTAR TODOS
public List<CadastroEntity> listarTodos() {
    return repository.findAll();
}

// BUSCAR POR ID
public CadastroEntity buscarPorId(Long id) {
    return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cadastro não encontrado"));
}

// SALVAR
public CadastroEntity salvar(CadastroEntity cadastro) {
    return repository.save(cadastro);
}

// ATUALIZAR
public CadastroEntity atualizar(Long id, CadastroEntity cadastro) {

    CadastroEntity existente = buscarPorId(id);

    existente.setNome(cadastro.getNome());
    existente.setSenha(cadastro.getSenha());
    existente.setEmail(cadastro.getEmail());
 

    return repository.save(existente);
}

// DELETAR
public void deletar(Long id) {
    repository.deleteById(id);
}
}
