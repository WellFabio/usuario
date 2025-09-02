package com.ExtremeBIke.usuario.business;

import com.ExtremeBIke.usuario.infrastructure.clients.ViaCepClient;
import com.ExtremeBIke.usuario.infrastructure.clients.ViaCepDTO;
import com.ExtremeBIke.usuario.infrastructure.exceptions.IllegalArgumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor

public class ViaCepService {

    private final ViaCepClient client;

    public ViaCepDTO buscarDadosEndereco(String cep){
        return  client.buscaDadosEndereco(processarCep(cep));

    }

    private String processarCep(String cep){
        String cepFormatado = cep.replace(" ", "").
                replace("_", "");

        if(!cepFormatado.matches("\\d+")
                || !Objects.equals(cepFormatado.length(), 8)){
            throw new IllegalArgumentException("O cep contém caracteres invalidos, favor verificar");
        }

        return cepFormatado;
    }
}
