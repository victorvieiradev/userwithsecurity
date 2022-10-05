package com.mycompany.usuarioapi.controllers;

import com.mycompany.usuarioapi.dtos.UsuarioDto;
import com.mycompany.usuarioapi.models.UsuarioModel;
import com.mycompany.usuarioapi.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    @GetMapping

    public ResponseEntity<List<UsuarioDto>> mostrarUsuarios(){
        return ResponseEntity.ok(usuarioService.listaDeUsuario());
    }


    @GetMapping(path = "/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id){
        Optional<UsuarioModel> usuarioModelOptional = usuarioService.buscarPorId(id);
        if (usuarioModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O usuário não foi encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarioModelOptional.get());
    }
    @PostMapping
    public UsuarioModel cadastrar(@RequestBody UsuarioModel usuario ){
        return usuarioService.cadastrarUsuario(usuario);
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> excluir(@PathVariable Long id ){
        Optional<UsuarioModel> usuarioModelO = usuarioService.buscarPorId(id);
        if (usuarioModelO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A conta não foi encontrada.");
        }
        usuarioService.excluirUsuario(usuarioModelO.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body("Excluido com sucesso.");
    }

}
