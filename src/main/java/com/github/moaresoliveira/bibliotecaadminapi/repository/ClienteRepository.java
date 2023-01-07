package com.github.moaresoliveira.bibliotecaadminapi.repository;

import com.github.moaresoliveira.bibliotecaadminapi.entity.ClienteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

    Page<ClienteEntity> findAll(Pageable pageable);

}
