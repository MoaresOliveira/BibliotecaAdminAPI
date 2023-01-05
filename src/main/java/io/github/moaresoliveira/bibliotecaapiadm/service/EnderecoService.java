package io.github.moaresoliveira.bibliotecaapiadm.service;

import io.github.moaresoliveira.bibliotecaapiadm.dto.EnderecoDTO;
import io.github.moaresoliveira.bibliotecaapiadm.entity.EnderecoEntity;
import io.github.moaresoliveira.bibliotecaapiadm.mapper.EnderecoMapper;
import io.github.moaresoliveira.bibliotecaapiadm.repository.EnderecoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final EnderecoMapper enderecoMapper;
    private final ViaCepService viaCepService;

    public EnderecoService(EnderecoRepository enderecoRepository, ViaCepService viaCepService) {
        this.enderecoRepository = enderecoRepository;
        this.viaCepService = viaCepService;
        enderecoMapper = EnderecoMapper.INSTANCE;
    }

    public EnderecoEntity cadastrar(EnderecoDTO form) {
        EnderecoEntity endereco = buscarEnderecoUnico(form);
        if(endereco == null) {
            EnderecoDTO enderecoEncontrado = viaCepService.buscarEnderecoPorCep(form.getCep());
            enderecoEncontrado.setComplemento(form.getComplemento());
            enderecoEncontrado.setNumero(form.getNumero());
            endereco = toEntity(enderecoEncontrado);
            return enderecoRepository.save(endereco);
        }
        return endereco;
    }

    public List<String> listarTodosCep() {
        return enderecoRepository.findAllDistinctCep();
    }

    public EnderecoEntity buscarEnderecoUnico(EnderecoDTO form){
        Optional<EnderecoEntity> endereco = enderecoRepository
                .findByCepAndComplementoAndNumero(form.getCep(), form.getComplemento(), form.getNumero());

        return endereco.orElse(null);
    }

    public EnderecoDTO buscarEnderecoPorCep(String cep) {
        return viaCepService.buscarEnderecoPorCep(cep);
    }

    private EnderecoEntity toEntity(EnderecoDTO dto) {
        return enderecoMapper.dtoToEntity(dto);
    }

    private EnderecoDTO toDto(EnderecoEntity entity) {
        return enderecoMapper.entityToDto(entity);
    }

}
