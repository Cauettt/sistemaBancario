package com.example.banco.sistemaBancario.service;

import com.example.banco.sistemaBancario.dto.ClienteDTO;
import com.example.banco.sistemaBancario.entity.Cliente;
import com.example.banco.sistemaBancario.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private ClienteDTO toDTO(Cliente cliente){
        return new ClienteDTO(cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getTelefone());
    }

    @Autowired
    private ClienteRepository clienteRepository;

    public List<ClienteDTO> listarClientes(){
        return clienteRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public Optional<ClienteDTO> buscarPorId(Long id){
        return clienteRepository.findById(id)
                .map(this::toDTO);
    }

    public ClienteDTO criarCliente(Cliente cliente){
        Cliente clienteSaved = clienteRepository.save(cliente);
        return toDTO(clienteSaved);
    }

    public void excluirCliente(Long id){
        clienteRepository.deleteById(id);
    }

    public ClienteDTO atualizarCliente(Long id, Cliente atualizarCliente){
        return clienteRepository.findById(id)
                .map(cliente -> {
                    cliente.setNome(atualizarCliente.getNome());
                    cliente.setCpf(atualizarCliente.getCpf());
                    cliente.setDataNasc(atualizarCliente.getDataNasc());
                    cliente.setEmail(atualizarCliente.getEmail());
                    cliente.setEndereco(atualizarCliente.getEndereco());
                    cliente.setSenha(atualizarCliente.getSenha());
                    cliente.setTelefone(atualizarCliente.getTelefone());

                    Cliente clienteSaved = clienteRepository.save(cliente);
                    return toDTO(clienteSaved);

                }).orElseThrow(() -> new RuntimeException("Cliente não existe"));
    }

}
