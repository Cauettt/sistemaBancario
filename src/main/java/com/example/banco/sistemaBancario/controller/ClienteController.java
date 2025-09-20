package com.example.banco.sistemaBancario.controller;

import com.example.banco.sistemaBancario.dto.ClienteDTO;
import com.example.banco.sistemaBancario.entity.Cliente;
import com.example.banco.sistemaBancario.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    private List<ClienteDTO> listarTodos(){
        return clienteService.listarClientes();
    }

    @GetMapping("/{id}")
    private ResponseEntity<ClienteDTO> buscarPorId(@PathVariable Long id){
        return clienteService.buscarPorId(id)
                .map(clienteDTO -> new ResponseEntity<>(clienteDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    private ClienteDTO criarCliente(@RequestBody Cliente cliente){
        return clienteService.criarCliente(cliente);
    }

    @PutMapping("/{id}")
    private ClienteDTO atualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente){
        return clienteService.atualizarCliente(id, cliente);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> excluirCliente(@PathVariable Long id){
        clienteService.excluirCliente(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


