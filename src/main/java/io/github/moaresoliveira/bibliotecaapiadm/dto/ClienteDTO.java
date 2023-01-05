package io.github.moaresoliveira.bibliotecaapiadm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ClienteDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    private String nome;

    private String documento;

    private EnderecoDTO endereco;

}
