package com.algaworks.algalog.domain.services;

import com.algaworks.algalog.domain.exceptions.NegocioException;
import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repositories.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Transactional
    public Cliente create(Cliente cliente) {
        boolean emailIsPresent = clienteRepository.findByEmail(cliente.getEmail())
                .stream()
                .anyMatch(c -> c.getEmail().equals(cliente.getEmail()));

        if (emailIsPresent) {
            throw new NegocioException("Já existe um cliente cadastrado com este email.");
        }

        return clienteRepository.save(cliente);
    }

    @Transactional
    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }
}
