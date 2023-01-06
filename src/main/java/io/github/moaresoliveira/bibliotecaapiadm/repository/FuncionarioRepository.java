package io.github.moaresoliveira.bibliotecaapiadm.repository;

import io.github.moaresoliveira.bibliotecaapiadm.entity.FuncionarioEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionarioRepository extends JpaRepository<FuncionarioEntity, Long> {

    Page<FuncionarioEntity> findAll(Pageable pageable);

    Integer countAllByEnderecoCep(String cep);

}
