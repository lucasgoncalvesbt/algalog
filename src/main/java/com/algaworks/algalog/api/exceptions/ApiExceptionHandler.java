package com.algaworks.algalog.api.exceptions;

import com.algaworks.algalog.domain.exceptions.NegocioException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status,
            WebRequest request
    ) {
        List<Problema.Campo> campos = new ArrayList<>();

        ex.getBindingResult().getFieldErrors()
                .forEach(e -> {
                    String nome = e.getField();
                    String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
                    campos.add(new Problema.Campo(nome, mensagem));
                });


        Problema problema = new Problema();
        problema.setStatus(status.value());
        problema.setDataHora(LocalDateTime.now());
        problema.setTitulo("Um ou mais campos inválidos na requisição");
        problema.setCampos(campos);

        return handleExceptionInternal(ex, problema, headers, status, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<Object> handleNegocioException(NegocioException ex, WebRequest request) {
        Problema problema = new Problema();
        problema.setStatus(HttpStatus.BAD_REQUEST.value());
        problema.setDataHora(LocalDateTime.now());
        problema.setTitulo(ex.getMessage());


        return handleExceptionInternal(ex, problema, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
