package com.github.moaresoliveira.bibliotecaadminapi.service;

import com.github.moaresoliveira.bibliotecaadminapi.dto.ClienteDTO;
import com.github.moaresoliveira.bibliotecaadminapi.entity.ClienteEntity;
import com.github.moaresoliveira.bibliotecaadminapi.entity.EnderecoEntity;
import com.github.moaresoliveira.bibliotecaadminapi.exception.ClienteNotFoundException;
import com.github.moaresoliveira.bibliotecaadminapi.mapper.ClienteMapper;
import com.github.moaresoliveira.bibliotecaadminapi.repository.ClienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;
    private EnderecoService enderecoService;
    private ClienteMapper clienteMapper;

    public ClienteService(ClienteRepository clienteRepository, EnderecoService enderecoService) {
        this.clienteRepository = clienteRepository;
        this.enderecoService = enderecoService;
        this.clienteMapper = ClienteMapper.INSTANCE;
    }

    public ClienteDTO cadastrar(ClienteDTO dto) {
        ClienteEntity entity = toEntity(dto);
        EnderecoEntity endereco = enderecoService.cadastrar(dto.getEndereco());
        entity.setEndereco(endereco);
        ClienteEntity clienteSalvo = clienteRepository.save(entity);
        return toDto(clienteSalvo);
    }

    public ClienteDTO atualizar(Long id, ClienteDTO dto) {
        ClienteEntity entity = clienteRepository.findById(id).orElseThrow(() -> new ClienteNotFoundException(id));
        entity.setNome(dto.getNome());
        entity.setDocumento(dto.getDocumento());

        EnderecoEntity endereco = enderecoService.cadastrar(dto.getEndereco());
        entity.setEndereco(endereco);

        ClienteEntity clienteSalvo = clienteRepository.save(entity);
        return toDto(clienteSalvo);
    }

    public void deletar(Long id) {
        ClienteEntity entity =
                clienteRepository.findById(id)
                        .orElseThrow(() -> new ClienteNotFoundException(id));
        clienteRepository.delete(entity);
    }

    public Page<ClienteDTO> listarTodos(Pageable pageable) {
        Page<ClienteEntity> entities = clienteRepository.findAll(pageable);
        return entities.map(this::toDto);
    }

    private ClienteEntity toEntity(ClienteDTO dto) {
        return clienteMapper.dtoToEntity(dto);
    }

    private ClienteDTO toDto(ClienteEntity entity) {
        return clienteMapper.entityToDto(entity);
    }


}
