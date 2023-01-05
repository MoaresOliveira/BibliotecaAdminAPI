package io.github.moaresoliveira.bibliotecaapiadm.repository;

import io.github.moaresoliveira.bibliotecaapiadm.entity.EnderecoEntity;
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
