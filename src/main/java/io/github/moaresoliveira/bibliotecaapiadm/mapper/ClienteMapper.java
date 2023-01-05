package io.github.moaresoliveira.bibliotecaapiadm.mapper;

import io.github.moaresoliveira.bibliotecaapiadm.dto.ClienteDTO;
import io.github.moaresoliveira.bibliotecaapiadm.entity.ClienteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClienteMapper {

    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "nome", target = "nome"),
            @Mapping(source = "documento", target = "documento"),
            @Mapping(source = "endereco", target = "endereco")
    })
    ClienteDTO entityToDto(ClienteEntity entity);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "nome", target = "nome"),
            @Mapping(source = "documento", target = "documento"),
            @Mapping(source = "endereco", target = "endereco")
    })
    ClienteEntity dtoToEntity(ClienteDTO dto);

}
