package com.kauarasera.clientcrud.repositories;

import com.kauarasera.clientcrud.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
