package io.github.moaresoliveira.bibliotecaapiadm.mapper;

import io.github.moaresoliveira.bibliotecaapiadm.dto.EnderecoDTO;
import io.github.moaresoliveira.bibliotecaapiadm.entity.EnderecoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EnderecoMapper {

    EnderecoMapper INSTANCE = Mappers.getMapper(EnderecoMapper.class);

    @Mappings({
            @Mapping(source = "cep", target = "cep"),
            @Mapping(source = "logradouro", target = "logradouro"),
            @Mapping(source = "numero", target = "numero"),
            @Mapping(source = "complemento", target = "complemento"),
            @Mapping(source = "bairro", target = "bairro"),
            @Mapping(source = "localidade", target = "localidade"),
            @Mapping(source = "uf", target = "uf")
    })
    EnderecoDTO entityToDto(EnderecoEntity entity);

    @Mappings({
            @Mapping(source = "cep", target = "cep"),
            @Mapping(source = "logradouro", target = "logradouro"),
            @Mapping(source = "numero", target = "numero"),
            @Mapping(source = "complemento", target = "complemento"),
            @Mapping(source = "bairro", target = "bairro"),
            @Mapping(source = "localidade", target = "localidade"),
            @Mapping(source = "uf", target = "uf")
    })
    EnderecoEntity dtoToEntity(EnderecoDTO dto);

}
