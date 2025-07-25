package com.ExtremeBIke.usuario.controller;

import com.ExtremeBIke.usuario.business.UsuarioService;
import com.ExtremeBIke.usuario.business.dto.EnderecoDTO;
import com.ExtremeBIke.usuario.business.dto.TelefoneDTO;
import com.ExtremeBIke.usuario.business.dto.UsuarioDTO;
import com.ExtremeBIke.usuario.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor

public class UsuarioController {

    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<UsuarioDTO> salvaUsuario(@RequestBody UsuarioDTO usuarioDTO){
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDTO));
    }

    @PostMapping("/login")
    public String login(@RequestBody UsuarioDTO usuarioDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usuarioDTO.getEmail(),usuarioDTO.getSenha())
        );
        return "Bearer " + jwtUtil.generateToken(authentication.getName());
    }

    @GetMapping

    public ResponseEntity<UsuarioDTO> buscaUsuarioPorEmail(@RequestParam("email") String email) {
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deletaUsuarioPorEmail(@PathVariable String email) {
        usuarioService.deletaUsuarioPorEmail(email);
        return ResponseEntity.ok() .build();
    }

    @PutMapping
    public ResponseEntity<UsuarioDTO> atualizDadosUsuario(@RequestBody UsuarioDTO dto,
                                                          @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.atualizacoaDadosUsuario(token,dto));
    }

    @PutMapping("/endereco")
    public ResponseEntity<EnderecoDTO> atualizaEndereco(@RequestBody EnderecoDTO dto,
                                                           @RequestParam("id") Long id){
        return ResponseEntity.ok(usuarioService.atualizaEndereco(id,dto));
    }

    @PutMapping("/telefone")
    public ResponseEntity<TelefoneDTO> atualizaTelefone(@RequestBody TelefoneDTO dto,
                                                           @RequestParam("id") Long id) {
        return ResponseEntity.ok(usuarioService.atualizaTelefone(id, dto));
    }

    @PostMapping("/endereco")
    public ResponseEntity<EnderecoDTO> cadastroEndereco(@RequestBody EnderecoDTO dto,
                                                        @RequestHeader("Authorization")String token){
        return ResponseEntity.ok(usuarioService.cadastraEndereco(token, dto));
    }

    @PostMapping("/telefone")
    public ResponseEntity<TelefoneDTO> cadastroTelefone(@RequestBody TelefoneDTO dto,
                                                        @RequestHeader("Authorization")String token) {
        return ResponseEntity.ok(usuarioService.cadastraTelefone(token, dto));
    }
}
