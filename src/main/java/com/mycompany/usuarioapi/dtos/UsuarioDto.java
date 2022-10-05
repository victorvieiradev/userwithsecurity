package com.mycompany.usuarioapi.dtos;

import com.mycompany.usuarioapi.models.UsuarioModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {
    private String nome;
    private int idade;
    private String tel;
    private String login;
    private String senha;

    public UsuarioDto(UsuarioModel usuarioModel){
        this.nome = usuarioModel.getNome();
        this.login = usuarioModel.getLogin();
        this.senha = usuarioModel.getSenha();
    }
    public static List<UsuarioDto> convert(List<UsuarioModel> usuarioModelList) {
        return usuarioModelList.stream().map(UsuarioDto::new).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "UsuarioDto{" +
                "nome='" + nome + '\'' +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
