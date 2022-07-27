package com.iberthy.backend.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<ErroException.Campo> campos = new ArrayList<>();
        for( ObjectError error : ex.getBindingResult().getAllErrors() ){
            var nome = ((FieldError) error).getField();
            var mensagem = error.getDefaultMessage();

            campos.add(new ErroException.Campo(nome, mensagem));
        }

        var erro = new ErroException();
        erro.setStatus(status.value());
        erro.setDataHora(LocalDateTime.now());
        erro.setTitulo("Um ou mais campos inválidos. Faça o preenchimento correto e tente novamente!");
        erro.setCampos(campos);

        return this.handleExceptionInternal(ex, erro, headers, status, request);
    }

}
