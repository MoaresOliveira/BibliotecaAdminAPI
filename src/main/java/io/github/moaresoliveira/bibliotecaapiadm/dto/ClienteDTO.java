package io.github.moaresoliveira.bibliotecaapiadm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Cliente", description = "Representa um cliente", subTypes = {
        EnderecoDTO.class
})
public class ClienteDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "1")
    private Long id;

    @Schema(name = "nome", example = "João da Silva")
    private String nome;

    @Schema(name = "documento", example = "123.456.789-00")
    private String documento;

    @Schema(name = "telefone", subTypes = {
            EnderecoDTO.class
    })
    private EnderecoDTO endereco;

}
