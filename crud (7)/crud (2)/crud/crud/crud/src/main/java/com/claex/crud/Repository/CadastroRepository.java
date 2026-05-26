package com.claex.crud.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
// Importa a interface do Spring Data JPA que já possui métodos prontos de CRUD

import org.springframework.stereotype.Repository;
// Indica que essa interface é um componente de acesso a dados (DAO)

import com.claex.crud.Entity.CadastroEntity;

@Repository

public interface CadastroRepository extends JpaRepository<CadastroEntity, Long>{

    Optional<CadastroEntity> findByNome(String nome);
    
}