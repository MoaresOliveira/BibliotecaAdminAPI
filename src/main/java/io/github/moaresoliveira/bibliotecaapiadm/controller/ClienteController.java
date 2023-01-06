package io.github.moaresoliveira.bibliotecaapiadm.controller;

import io.github.moaresoliveira.bibliotecaapiadm.dto.ClienteDTO;
import io.github.moaresoliveira.bibliotecaapiadm.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cliente")
@Tag(name = "Cliente", description = "Cliente Controller")
public class ClienteController {

    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Operation(summary = "Cadastrar Cliente")
    @PostMapping("/cadastrar")
    public ResponseEntity<ClienteDTO> cadastrar(@RequestBody ClienteDTO dto) {
        ClienteDTO clienteSalvo = clienteService.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
    }

    @Operation(summary = "Atualizar Cliente")
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ClienteDTO> atualizar(@PathVariable Long id, @RequestBody ClienteDTO dto) {
        ClienteDTO clienteAtualizado = clienteService.atualizar(id, dto);
        return ResponseEntity.accepted().body(clienteAtualizado);
    }

    @Operation(summary = "Deletar Cliente")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        clienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Listar Clientes")
    @GetMapping("/listar")
    public ResponseEntity<Page<ClienteDTO>> listarTodos(Pageable pageable) {
        Page<ClienteDTO> clienteListado = clienteService.listarTodos(pageable);
        if (clienteListado.hasContent()) {
            return ResponseEntity.ok(clienteListado);
        }
        return ResponseEntity.noContent().build();
    }

}
