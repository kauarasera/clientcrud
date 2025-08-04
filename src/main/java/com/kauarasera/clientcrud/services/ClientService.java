package com.kauarasera.clientcrud.services;

import com.kauarasera.clientcrud.dto.ClientDTO;
import com.kauarasera.clientcrud.entities.Client;
import com.kauarasera.clientcrud.repositories.ClientRepository;
import com.kauarasera.clientcrud.services.exceptions.DatabaseException;
import com.kauarasera.clientcrud.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
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

        // Minimum age validation
        int age = Period.between(dto.getBirthDate(), LocalDate.now()).getYears();
        if (age < 18) {
            throw new DatabaseException("Client must be at least 18 years old");
        }

        // Checks if CPF already exists in the bank
        if (clientRepository.existsByCpf(dto.getCpf())) {
            throw new DatabaseException("CPF already registered");
        }
        Client entity = new Client();
        entity = clientRepository.save(entity);
        return new ClientDTO(entity);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto) {
        try {
            Client entity = clientRepository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = clientRepository.save(entity);
            return new ClientDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Resource not found");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS) // Só executa a transacao se estiver no contexto de outra transcacao
    public void delete(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Resource not found");
        }
        try {
            clientRepository.deleteById(id); //Passo o id para deletar e o repository vai até o banco de dados para deletar
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Referential integrity failure"); // Se tentar apagar um produto que já tenha um pedido vai dar falha de integridade
            //a API não pode apagar um produto com ele vinculado a algum pedido.
        }
    }

    private void copyDtoToEntity(ClientDTO dto, Client entity) {
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
    }
}