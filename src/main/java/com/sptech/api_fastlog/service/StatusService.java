package com.sptech.api_fastlog.service;

import com.sptech.api_fastlog.constants.EntregaConstants;
import com.sptech.api_fastlog.constants.StatusConstants;
import com.sptech.api_fastlog.domain.Status;
import com.sptech.api_fastlog.repository.StatusRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusService {
    private final StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public List<Status> findAllStatus(){
        return statusRepository.findAll();
    }

    public Status getStatusById(Long id){
        Optional<Status> foundStatus = statusRepository.findById(id);
        if(foundStatus.isEmpty()){
            throw new IllegalArgumentException(StatusConstants.ID_NAO_ENCONTRADO);
        }

        return foundStatus.get();
    }

    public void createStatus(Status status){
        statusRepository.save(status);
    }

    @Transactional
    public Status updateStatus(Long idStatus, Status statusAtualizado) {
        Status statusExistente = statusRepository.findById(idStatus)
                .orElseThrow(() -> new IllegalArgumentException(StatusConstants.ID_NAO_ENCONTRADO + idStatus));

        statusExistente.setDescricao(statusAtualizado.getDescricao());
        statusExistente.setTipo(statusAtualizado.getTipo());

        return statusRepository.save(statusExistente);
    }

    public void deleteStatusById(Long id){
        Optional<Status> foundStatus = statusRepository.findById(id);
        if(foundStatus.isEmpty()){
            throw new IllegalArgumentException(StatusConstants.ID_NAO_ENCONTRADO);
        }

        statusRepository.delete(foundStatus.get());
    }
}
