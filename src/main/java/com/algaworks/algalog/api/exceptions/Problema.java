package com.algaworks.algalog.api.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(Include.NON_NULL)
@Getter
@Setter
public class Problema {

    private Integer status;
    private LocalDateTime dataHora;
    private String titulo;

    List<Campo> campos;

    @AllArgsConstructor
    @Getter
    public static class Campo {
        private String nome;
        private String mensagem;
    }

}