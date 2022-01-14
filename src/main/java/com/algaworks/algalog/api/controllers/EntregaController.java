package com.algaworks.algalog.api.controllers;

import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.services.EntregaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/entregas")
public class EntregaController {

    private final EntregaService entregaService;

    @PostMapping
    public ResponseEntity<Entrega> solicitation(@RequestBody Entrega entrega) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(entregaService.solicitation(entrega));

    }

}
