package com.github.moaresoliveira.bibliotecaadminapi.mapper;

import com.github.moaresoliveira.bibliotecaadminapi.dto.FuncionarioDTO;
import com.github.moaresoliveira.bibliotecaadminapi.entity.FuncionarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface FuncionarioMapper {

    FuncionarioMapper INSTANCE = Mappers.getMapper(FuncionarioMapper.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "nome", target = "nome"),
            @Mapping(source = "documento", target = "documento"),
            @Mapping(source = "endereco", target = "endereco")
    })
    FuncionarioDTO entityToDto(FuncionarioEntity entity);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "nome", target = "nome"),
            @Mapping(source = "documento", target = "documento"),
            @Mapping(source = "endereco", target = "endereco")
    })
    FuncionarioEntity dtoToEntity(FuncionarioDTO dto);

    List<FuncionarioDTO> entityToDto(List<FuncionarioEntity> entity);

    List<FuncionarioEntity> dtoToEntity(List<FuncionarioDTO> dto);

}
