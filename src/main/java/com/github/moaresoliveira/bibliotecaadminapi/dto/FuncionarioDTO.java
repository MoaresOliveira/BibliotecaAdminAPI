package com.github.moaresoliveira.bibliotecaadminapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Funcionario", description = "Representa um funcionário", subTypes = {
        EnderecoDTO.class
})
public class FuncionarioDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "1")
    private Long id;

    @Schema(name = "nome", example = "Maria Antonieta")
    private String nome;

    @Schema(name = "documento", example = "123.456.789-00")
    private String documento;

    @Schema(name = "endereco")
    private EnderecoDTO endereco;

}
