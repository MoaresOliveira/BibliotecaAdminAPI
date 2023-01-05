package io.github.moaresoliveira.bibliotecaapiadm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FuncionarioNotFoundException extends RuntimeException {

    public FuncionarioNotFoundException(Long id) {
        super("Funcionário não encontrado com o id: " + id.toString());
    }

}
