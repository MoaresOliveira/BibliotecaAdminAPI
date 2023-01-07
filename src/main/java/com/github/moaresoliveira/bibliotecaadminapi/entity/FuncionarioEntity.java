package com.github.moaresoliveira.bibliotecaadminapi.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "TB_FUNCIONARIO")
public class FuncionarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String documento;

    @ManyToOne
    private EnderecoEntity endereco;

}
