package io.github.moaresoliveira.bibliotecaapiadm.service;

import io.github.moaresoliveira.bibliotecaapiadm.dto.EnderecoDTO;
import io.github.moaresoliveira.bibliotecaapiadm.dto.FuncionarioDTO;
import io.github.moaresoliveira.bibliotecaapiadm.dto.RelatorioFuncionarioDTO;
import io.github.moaresoliveira.bibliotecaapiadm.entity.EnderecoEntity;
import io.github.moaresoliveira.bibliotecaapiadm.entity.FuncionarioEntity;
import io.github.moaresoliveira.bibliotecaapiadm.exception.FuncionarioNotFoundException;
import io.github.moaresoliveira.bibliotecaapiadm.mapper.FuncionarioMapper;
import io.github.moaresoliveira.bibliotecaapiadm.repository.FuncionarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Page<FuncionarioDTO> listarTodos(int page, int size, String sortBy, String direction) {
        Sort sort = Sort.by(sortBy).ascending();
        if(direction.equalsIgnoreCase("desc")) {
            sort = Sort.by(sortBy).descending();
        }
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<FuncionarioEntity> entities = funcionarioRepository.findAll(pageable);
        return entities.map(this::toDto);
    }

    public List<FuncionarioDTO> listarTodosPorCep(String cep) {
        List<FuncionarioEntity> entities = funcionarioRepository.findAllByEnderecoCep(cep);
        return toDto(entities);
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

    private List<FuncionarioDTO> toDto(List<FuncionarioEntity> entity) {
        return funcionarioMapper.entityToDto(entity);
    }

}
