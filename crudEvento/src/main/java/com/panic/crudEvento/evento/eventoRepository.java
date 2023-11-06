package com.panic.crudEvento.evento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface eventoRepository extends JpaRepository<evento, Long> {
    Optional<evento> findByName(String name);
}
