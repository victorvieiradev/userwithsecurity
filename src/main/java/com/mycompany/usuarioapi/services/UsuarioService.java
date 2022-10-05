package com.mycompany.usuarioapi.services;

import com.mycompany.usuarioapi.dtos.UsuarioDto;
import com.mycompany.usuarioapi.models.UsuarioModel;
import com.mycompany.usuarioapi.repositories.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    private BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public List<UsuarioDto> listaDeUsuario() {
        List<UsuarioModel> usuarios = usuarioRepository.findAll();
        return UsuarioDto.convert(usuarios);
    }
    public Optional<UsuarioModel> buscarPorId(Long id){
        return usuarioRepository.findById(id);
    }
    public UsuarioModel cadastrarUsuario(UsuarioModel usuario ){
        usuario.setSenha(passwordEncoder().encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }
    public void excluirUsuario(Long id){
        usuarioRepository.deleteById(id);
    }
}
