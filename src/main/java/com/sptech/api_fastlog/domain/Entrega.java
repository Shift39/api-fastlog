package com.sptech.api_fastlog.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Entrega {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEntrega;

    @NotBlank
    private String nomeEntrega;

    @NotBlank
    private Status statusEntrega;

    public Long getIdEntrega() {
        return idEntrega;
    }

    public void setIdEntrega(Long idEntrega) {
        this.idEntrega = idEntrega;
    }

    public @NotBlank String getNomeEntrega() {
        return nomeEntrega;
    }

    public void setNomeEntrega(@NotBlank String nomeEntrega) {
        this.nomeEntrega = nomeEntrega;
    }

    public @NotBlank Status getStatusEntrega() {
        return statusEntrega;
    }

    public void setStatusEntrega(@NotBlank Status statusEntrega) {
        this.statusEntrega = statusEntrega;
    }
}
