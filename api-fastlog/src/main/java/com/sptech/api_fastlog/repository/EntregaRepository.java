package com.sptech.api_fastlog.repository;

import com.sptech.api_fastlog.domain.Entrega;
import com.sptech.api_fastlog.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long> {
    List<Entrega> findByStatusEntrega(Status status);

    List<Entrega> findByStatusEntregaDescricao(String descricao);
}
