package com.kauarasera.clientcrud.controllers;

import com.kauarasera.clientcrud.entities.Client;
import com.kauarasera.clientcrud.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping
    public String test() {
        Optional<Client> result = clientRepository.findById(1L);
        Client client = result.get();
        return client.getName();
    }
}
