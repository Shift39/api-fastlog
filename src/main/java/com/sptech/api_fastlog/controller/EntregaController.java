package com.sptech.api_fastlog.controller;
import com.sptech.api_fastlog.domain.Entrega;
import com.sptech.api_fastlog.domain.Status;
import com.sptech.api_fastlog.service.EntregaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entregas")
public class EntregaController {

    private final EntregaService entregaService;

    public EntregaController(EntregaService entregaService) {
        this.entregaService = entregaService;
    }

    @GetMapping
    public ResponseEntity<List<Entrega>> getAllEntregas() {
        List<Entrega> entregas = entregaService.getAllEntregas();
        return ResponseEntity.ok(entregas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEntregaById(@PathVariable Long id) {
        try {
            Entrega entrega = entregaService.getEntregaById(id);
            return ResponseEntity.ok(entrega);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createEntrega(@Valid @RequestBody Entrega entrega) {
        try {
            entregaService.createEntrega(entrega);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarEntrega(
            @PathVariable Long id,
            @RequestBody Entrega entregaAtualizada) {
        try {
            Entrega entrega = entregaService.atualizarEntrega(id, entregaAtualizada);
            return ResponseEntity.ok(entrega);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEntregaById(@PathVariable Long id) {
        try {
            entregaService.deleteEntregaById(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/status")
    public ResponseEntity<?> buscarPorStatus(@RequestBody Status status) {
        try {
            List<Entrega> entregas = entregaService.buscarPorStatus(status);
            return ResponseEntity.ok(entregas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao buscar entregas pelo status: " + e.getMessage());
        }
    }

    @GetMapping("/status/{descricao}")
    public ResponseEntity<?> buscarPorDescricaoDeStatus(@PathVariable String descricao) {
        try {
            List<Entrega> entregas = entregaService.buscarPorDescricaoDeStatus(descricao);
            return ResponseEntity.ok(entregas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao buscar entregas pela descrição do status: " + e.getMessage());
        }
    }

}