package com.ExtremeBIke.usuario.business.converter;

import com.ExtremeBIke.usuario.business.dto.EnderecoDTO;
import com.ExtremeBIke.usuario.business.dto.TelefoneDTO;
import com.ExtremeBIke.usuario.business.dto.UsuarioDTO;
import com.ExtremeBIke.usuario.infrastructure.entity.Endereco;
import com.ExtremeBIke.usuario.infrastructure.entity.Telefone;
import com.ExtremeBIke.usuario.infrastructure.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component

public class UsuarioConverter {

    public Usuario paraUsuario(UsuarioDTO usuarioDTO){
       return Usuario.builder()
               .nome(usuarioDTO.getNome())
               .email(usuarioDTO.getEmail())
               .senha(usuarioDTO.getSenha())
               .enderecos(paraListaEndereco(usuarioDTO.getEnderecos()))
               .telefones(paraListaTelefones(usuarioDTO.getTelefone()))

               .build();
    }

    public List<Endereco> paraListaEndereco(List<EnderecoDTO> enderecoDTOS){
        return enderecoDTOS.stream().map(this::paraEndereco).toList();
    }

    public Endereco paraEndereco(EnderecoDTO enderecoDTO){
        return Endereco.builder()
                .rua(enderecoDTO.getRua())
                .numero(enderecoDTO.getNumero())
                .cidade(enderecoDTO.getCidade())
                .complemento(enderecoDTO.getComplemento())
                .cep(enderecoDTO.getCep())
                .estado(enderecoDTO.getEstado())
                .build();

    }

    public List<Telefone> paraListaTelefones(List<TelefoneDTO> telefoneDTOS){
        return telefoneDTOS.stream().map(this::paraTelefone).toList();
    }
    public Telefone paraTelefone(TelefoneDTO telefoneDTO){
        return Telefone.builder()
                .numero(telefoneDTO.getNumero())
                .ddd(telefoneDTO.getDdd())
                .build();
    }


        public UsuarioDTO paraUsuarioDTO(Usuario usuarioDTO){
            return UsuarioDTO.builder()
                    .nome(usuarioDTO.getNome())
                    .email(usuarioDTO.getEmail())
                    .senha(usuarioDTO.getSenha())
                    .enderecos(paraListaEnderecoDTO(usuarioDTO.getEnderecos()))
                    .telefone(paraListaTelefonesDTO(usuarioDTO.getTelefones()))

                    .build();
        }


        public List<EnderecoDTO> paraListaEnderecoDTO(List<Endereco> enderecoDTOS){
           List<EnderecoDTO> enderecos = new ArrayList<>();
           for(Endereco enderecoDTO : enderecoDTOS){
               enderecos.add(paraEnderecoDTO(enderecoDTO));
           }
           return enderecos;
        }

        public EnderecoDTO paraEnderecoDTO(Endereco enderecoDTO){
            return EnderecoDTO.builder()
                    .rua(enderecoDTO.getRua())
                    .numero(enderecoDTO.getNumero())
                    .cidade(enderecoDTO.getCidade())
                    .complemento(enderecoDTO.getComplemento())
                    .cep(enderecoDTO.getCep())
                    .estado(enderecoDTO.getEstado())
                    .build();

        }

        public List<TelefoneDTO> paraListaTelefonesDTO(List<Telefone> telefoneDTOS){
            return telefoneDTOS.stream().map(this::paraTelefoneDTO).toList();
        }
        public TelefoneDTO paraTelefoneDTO(Telefone telefoneDTO){
            return TelefoneDTO.builder()
                    .numero(telefoneDTO.getNumero())
                    .ddd(telefoneDTO.getDdd())
                    .build();
        }
}
