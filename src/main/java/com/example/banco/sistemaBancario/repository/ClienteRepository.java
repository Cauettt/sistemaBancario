package com.example.banco.sistemaBancario.repository;

import com.example.banco.sistemaBancario.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
