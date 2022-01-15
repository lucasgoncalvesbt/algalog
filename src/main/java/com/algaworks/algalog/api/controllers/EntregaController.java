package com.algaworks.algalog.api.controllers;

import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.repositories.EntregaRepository;
import com.algaworks.algalog.domain.services.EntregaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/entregas")
public class EntregaController {

    private final EntregaRepository entregaRepository;
    private final EntregaService entregaService;

    @PostMapping
    public ResponseEntity<Entrega> solicitation(@Valid @RequestBody Entrega entrega) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(entregaService.solicitation(entrega));

    }

    @GetMapping
    public ResponseEntity<List<Entrega>> findAll() {

        return ResponseEntity.ok(entregaRepository.findAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<Entrega> findById(@PathVariable Long id) {

        return entregaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

}
