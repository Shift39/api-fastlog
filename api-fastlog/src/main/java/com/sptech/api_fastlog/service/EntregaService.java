package com.sptech.api_fastlog.service;

import com.sptech.api_fastlog.constants.EntregaConstants;
import com.sptech.api_fastlog.domain.Entrega;
import com.sptech.api_fastlog.domain.Status;
import com.sptech.api_fastlog.repository.EntregaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntregaService {
    private final EntregaRepository entregaRepository;

    public EntregaService(EntregaRepository entregaRepository) {
        this.entregaRepository = entregaRepository;
    }

    public List<Entrega> getAllEntregas(){
        return entregaRepository.findAll();
    }

    public Entrega getEntregaById(Long id){
        Optional<Entrega> foundEntrega = entregaRepository.findById(id);
        if(foundEntrega.isEmpty()){
            throw new IllegalArgumentException(EntregaConstants.ID_NAO_ENCONTRADO);
        }

        return foundEntrega.get();
    }

    public void createEntrega(Entrega entrega){
        entregaRepository.save(entrega);
    }

    @Transactional
    public Entrega atualizarEntrega(Long idEntrega, Entrega entregaAtualizada) {
        Entrega entregaExistente = entregaRepository.findById(idEntrega)
                .orElseThrow(() -> new IllegalArgumentException(EntregaConstants.ID_NAO_ENCONTRADO + idEntrega));

        entregaExistente.setNomeEntrega(entregaAtualizada.getNomeEntrega());
        entregaExistente.setStatusEntrega(entregaAtualizada.getStatusEntrega());

        return entregaRepository.save(entregaExistente);
    }

    public void deleteEntregaById(Long id){
        Optional<Entrega> foundEntrega = entregaRepository.findById(id);
        if(foundEntrega.isEmpty()){
            throw new IllegalArgumentException(EntregaConstants.ID_NAO_ENCONTRADO);
        }

        entregaRepository.delete(foundEntrega.get());
    }

    public List<Entrega> buscarPorStatus(Status status) {
        return entregaRepository.findByStatusEntrega(status);
    }

    public List<Entrega> buscarPorDescricaoDeStatus(String descricao) {
        return entregaRepository.findByStatusEntregaDescricao(descricao);
    }
}
