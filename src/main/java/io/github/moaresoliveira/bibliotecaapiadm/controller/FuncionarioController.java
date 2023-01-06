package io.github.moaresoliveira.bibliotecaapiadm.controller;

import io.github.moaresoliveira.bibliotecaapiadm.dto.ClienteDTO;
import io.github.moaresoliveira.bibliotecaapiadm.dto.FuncionarioDTO;
import io.github.moaresoliveira.bibliotecaapiadm.dto.RelatorioFuncionarioDTO;
import io.github.moaresoliveira.bibliotecaapiadm.service.FuncionarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/funcionario")
@Tag(name = "Funcionario", description = "Funcionario Controller")
public class FuncionarioController {

    private FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @Operation(summary = "Cadastrar Funcionario")
    @PostMapping("/cadastrar")
    public ResponseEntity<FuncionarioDTO> cadastrar(@RequestBody FuncionarioDTO dto) {
        FuncionarioDTO funcionarioSalvo = funcionarioService.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioSalvo);
    }

    @Operation(summary = "Atualizar Funcionario")
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<FuncionarioDTO> atualizar(@PathVariable Long id, @RequestBody FuncionarioDTO dto) {
        FuncionarioDTO funcionarioAtualizado = funcionarioService.atualizar(id, dto);
        return ResponseEntity.ok(funcionarioAtualizado);
    }

    @Operation(summary = "Deletar Funcionario")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        funcionarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Listar Funcionarios")
    @GetMapping("/listar")
    public ResponseEntity<Page<FuncionarioDTO>> listarTodos(Pageable pageable) {
        Page<FuncionarioDTO> clienteListado = funcionarioService.listarTodos(pageable);
        if (clienteListado.hasContent()) {
            return ResponseEntity.ok(clienteListado);
        }
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Contar Funcionario por CEP")
    @GetMapping("/contar")
    public ResponseEntity<RelatorioFuncionarioDTO> getFuncionarios() {
        RelatorioFuncionarioDTO relatorioFuncionarioDTO = funcionarioService.contarFuncionariosPorCep();

        if(relatorioFuncionarioDTO == null){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(relatorioFuncionarioDTO);
    }

}
