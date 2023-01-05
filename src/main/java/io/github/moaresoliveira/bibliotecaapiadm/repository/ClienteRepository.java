package io.github.moaresoliveira.bibliotecaapiadm.repository;

import io.github.moaresoliveira.bibliotecaapiadm.entity.ClienteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

    Page<ClienteEntity> findAll(Pageable pageable);

}
