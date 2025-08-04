package com.kauarasera.clientcrud.services;

import com.kauarasera.clientcrud.dto.ClientDTO;
import com.kauarasera.clientcrud.entities.Client;
import com.kauarasera.clientcrud.repositories.ClientRepository;
import com.kauarasera.clientcrud.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Resource Not Found"));
        return new ClientDTO(client);
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable) {
        Page<Client> result = clientRepository.findAll(pageable);
        return result.map(x -> new ClientDTO(x));
    }

    @Transactional
    public ClientDTO insert(ClientDTO dto) {
        Client entity = new Client();
        entity = clientRepository.save(entity);
        return new ClientDTO(entity);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto) {
        Client entity = clientRepository.getReferenceById(id);
        copyDtoToEntity(dto, entity);
        entity = clientRepository.save(entity);
        return new ClientDTO(entity);
    }

    @Transactional
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    private void copyDtoToEntity(ClientDTO dto, Client entity) {
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
    }
}