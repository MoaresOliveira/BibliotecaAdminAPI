package com.github.moaresoliveira.bibliotecaadminapi.service;

import com.github.moaresoliveira.bibliotecaadminapi.dto.EnderecoDTO;
import com.github.moaresoliveira.bibliotecaadminapi.dto.FuncionarioDTO;
import com.github.moaresoliveira.bibliotecaadminapi.entity.EnderecoEntity;
import com.github.moaresoliveira.bibliotecaadminapi.mapper.FuncionarioMapper;
import com.github.moaresoliveira.bibliotecaadminapi.repository.FuncionarioRepository;
import com.github.moaresoliveira.bibliotecaadminapi.entity.FuncionarioEntity;
import com.github.moaresoliveira.bibliotecaadminapi.exception.FuncionarioNotFoundException;
import com.github.moaresoliveira.bibliotecaadminapi.dto.RelatorioFuncionarioDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final EnderecoService enderecoService;
    private final FuncionarioMapper funcionarioMapper;

    public FuncionarioService(FuncionarioRepository funcionarioRepository, EnderecoService enderecoService) {
        this.funcionarioRepository = funcionarioRepository;
        this.enderecoService = enderecoService;
        this.funcionarioMapper = FuncionarioMapper.INSTANCE;
    }

    public FuncionarioDTO cadastrar(FuncionarioDTO dto) {
        FuncionarioEntity entity = toEntity(dto);
        EnderecoEntity endereco = enderecoService.cadastrar(dto.getEndereco());
        entity.setEndereco(endereco);
        FuncionarioEntity funcionarioSalvo = funcionarioRepository.save(entity);

        return toDto(funcionarioSalvo);
    }

    public FuncionarioDTO atualizar(Long id, FuncionarioDTO dto) {
        FuncionarioEntity entity =
                funcionarioRepository.findById(id)
                        .orElseThrow(() -> new FuncionarioNotFoundException(id));

        entity.setNome(dto.getNome());
        entity.setDocumento(dto.getDocumento());
        EnderecoEntity enderecoUnico = enderecoService.buscarEnderecoUnico(dto.getEndereco());

        if (enderecoUnico == null) {
            EnderecoEntity endereco = enderecoService.cadastrar(dto.getEndereco());
            entity.setEndereco(endereco);
        } else {
            entity.setEndereco(enderecoUnico);
        }

        FuncionarioEntity funcionario = funcionarioRepository.save(entity);
        return toDto(funcionario);
    }

    public void deletar(Long id) {
        FuncionarioEntity entity =
                funcionarioRepository.findById(id)
                        .orElseThrow(() -> new FuncionarioNotFoundException(id));
        funcionarioRepository.delete(entity);
    }

    public Page<FuncionarioDTO> listarTodos(Pageable pageable) {
        Page<FuncionarioEntity> entities = funcionarioRepository.findAll(pageable);
        return entities.map(this::toDto);
    }

    public RelatorioFuncionarioDTO contarFuncionariosPorCep() {
        List<String> cepList = enderecoService.listarTodosCep();
        List<RelatorioFuncionarioDTO> funcionariosPorCep = new ArrayList<>();

        cepList.forEach(cep -> {
            RelatorioFuncionarioDTO relatorio = new RelatorioFuncionarioDTO();

            Integer quantidadeFuncionarios = funcionarioRepository.countAllByEnderecoCep(cep);
            EnderecoDTO endereco = enderecoService.buscarEnderecoPorCep(cep);

            relatorio.setQuantidadeFuncionarios(quantidadeFuncionarios);
            relatorio.setEndereco(endereco);

            funcionariosPorCep.add(relatorio);
        });

        funcionariosPorCep.sort((o1, o2) -> o2.getQuantidadeFuncionarios().compareTo(o1.getQuantidadeFuncionarios()));

        return funcionariosPorCep.get(0);
    }

    private FuncionarioEntity toEntity(FuncionarioDTO dto) {
        return funcionarioMapper.dtoToEntity(dto);
    }

    private FuncionarioDTO toDto(FuncionarioEntity entity) {
        return funcionarioMapper.entityToDto(entity);
    }

}
