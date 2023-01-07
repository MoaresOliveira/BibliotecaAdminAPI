package com.github.moaresoliveira.bibliotecaadminapi.repository;

import com.github.moaresoliveira.bibliotecaadminapi.entity.FuncionarioEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<FuncionarioEntity, Long> {

    Page<FuncionarioEntity> findAll(Pageable pageable);

    Integer countAllByEnderecoCep(String cep);

}
