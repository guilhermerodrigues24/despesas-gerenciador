package despesas.gerenciador.controller;

import despesas.gerenciador.model.Usuario;
import despesas.gerenciador.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/usuario")
@Validated
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<?> autenticarUsuario(@RequestBody Usuario usuario) {
        String email = usuario.getEmail();
        String senha = usuario.getSenha();

        // Autentica o usuário
        if (usuarioService.autenticarUsuario(email, senha)) {
            // Recupera o ID do usuário do banco de dados
            Long idDoUsuario = usuarioService.buscarIdDoUsuarioPorEmail(email);

            // Retorna a resposta com o ID do usuário
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("message", "Usuário autenticado com sucesso!");
            responseBody.put("userId", idDoUsuario);
            return ResponseEntity.ok().body(responseBody);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\": \"Email ou senha incorretos\"}");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable Long id) {
        Usuario usuario = usuarioService.buscarUsuarioPorId(id);
        return ResponseEntity.ok().body(usuario);
    }

    @PostMapping
    public ResponseEntity<Void> criarUsuario(@RequestBody Usuario usuario) {
        usuarioService.criarUsuario(usuario);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarUsuario(@RequestBody Usuario usuario, @PathVariable Long id) {
        usuario.setId(id);
        usuarioService.atualizarUsuario(usuario);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }

}