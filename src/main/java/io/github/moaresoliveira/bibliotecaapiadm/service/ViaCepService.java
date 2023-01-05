package io.github.moaresoliveira.bibliotecaapiadm.service;

import io.github.moaresoliveira.bibliotecaapiadm.dto.EnderecoDTO;
import io.github.moaresoliveira.bibliotecaapiadm.exception.CepInvalidoException;
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
