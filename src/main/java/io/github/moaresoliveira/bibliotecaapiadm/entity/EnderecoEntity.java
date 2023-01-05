package io.github.moaresoliveira.bibliotecaapiadm.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "TB_ENDERECO")
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cep;

    private String logradouro;

    private Integer numero;

    private String complemento;

    private String bairro;

    private String localidade;

    private String uf;

}
