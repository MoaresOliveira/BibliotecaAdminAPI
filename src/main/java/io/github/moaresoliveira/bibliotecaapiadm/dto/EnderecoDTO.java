package io.github.moaresoliveira.bibliotecaapiadm.dto;

import lombok.Data;

@Data
public class EnderecoDTO {

    private String cep;

    private String logradouro;

    private Integer numero;

    private String complemento;

    private String bairro;

    private String localidade;

    private String uf;

}
