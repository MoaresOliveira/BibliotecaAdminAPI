package com.github.moaresoliveira.bibliotecaadminapi.repository;

import com.github.moaresoliveira.bibliotecaadminapi.entity.EnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Long> {

    @Query("SELECT DISTINCT e.cep FROM EnderecoEntity e")
    List<String> findAllDistinctCep();

    Optional<EnderecoEntity> findByCepAndComplementoAndNumero(String cep, String complemento, Integer numero);

}
