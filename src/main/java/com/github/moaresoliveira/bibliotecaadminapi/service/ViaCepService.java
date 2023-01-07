package com.github.moaresoliveira.bibliotecaadminapi.service;

import com.github.moaresoliveira.bibliotecaadminapi.exception.CepInvalidoException;
import com.github.moaresoliveira.bibliotecaadminapi.dto.EnderecoDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class ViaCepService {

    public EnderecoDTO buscarEnderecoPorCep(String cep) {
        String uri = "https://viacep.com.br/ws/" + cep + "/json/";
        RestTemplate restTemplate = new RestTemplate();
        EnderecoDTO enderecoDTO = null;
        try {
            enderecoDTO = restTemplate.getForObject(uri, EnderecoDTO.class);
        } catch (RestClientException e) {
            throw new CepInvalidoException(cep);
        }
        return enderecoDTO;
    }

}
