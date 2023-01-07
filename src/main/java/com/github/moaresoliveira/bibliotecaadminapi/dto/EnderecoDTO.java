package com.github.moaresoliveira.bibliotecaadminapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Endereco", description = "Representa um endereço")
public class EnderecoDTO {

    @Schema(name = "cep", example = "01001-000")
    private String cep;

    @Schema(name = "logradouro", example = "Praça da Sé")
    private String logradouro;

    @Schema(name = "numero", example = "100")
    private Integer numero;

    @Schema(name = "complemento", example = "lado ímpar")
    private String complemento;

    @Schema(name = "bairro", example = "Sé")
    private String bairro;

    @Schema(name = "localidade", example = "São Paulo")
    private String localidade;

    @Schema(name = "uf", example = "SP")
    private String uf;

}
