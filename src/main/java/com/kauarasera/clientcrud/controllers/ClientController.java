package com.kauarasera.clientcrud.controllers;

import com.kauarasera.clientcrud.dto.ClientDTO;
import com.kauarasera.clientcrud.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/{id}")
    public ClientDTO findById(@PathVariable Long id) {
        ClientDTO dto = clientService.findById(id);
        return dto;
    }

    @GetMapping
    public Page<ClientDTO> findAll(Pageable pageable) {
        Page<ClientDTO> dto = clientService.findAll(pageable);
        return dto;
    }
}
