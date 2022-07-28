package com.iberthy.backend.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ GenericException.class })
    public ResponseEntity<Object> genericExeptionViolation(final GenericException ex, final WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        var erro = new ErroException();
        erro.setStatus(status.value());
        erro.setDataHora(LocalDateTime.now());
        erro.setTitulo(ex.getMessage());

        return handleExceptionInternal(ex, erro, new HttpHeaders(), status, request);
    }

    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> handleConstraintViolation(final ConstraintViolationException ex, final WebRequest request) {

//      Gera um erro casso uma constrait do bean validation for violada

        HttpStatus status = HttpStatus.BAD_REQUEST;

        List<ErroException.Campo> campos = new ArrayList<>();
        for( final ConstraintViolation<?> violation : ex.getConstraintViolations()){
            var nome = violation.getPropertyPath().toString();
            var mensagem = violation.getMessage();

            campos.add(new ErroException.Campo(nome, mensagem));
        }

        var erro = new ErroException();
        erro.setStatus(status.value());
        erro.setDataHora(LocalDateTime.now());
        erro.setTitulo("Um ou mais campos inválidos. Faça o preenchimento correto e tente novamente!");
        erro.setCampos(campos);

        return this.handleExceptionInternal(ex, erro, null, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

//      Se ouver um valid na frente do objeto que vai ser validado no argumento da requisição, ele chama essa função
//      Com isso eu posso fazer duas validações distintas, uma no DTO que é recebido e outra no objeto que vai ser salvo, assim temos uma dupla validação

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
