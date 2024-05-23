package despesas.gerenciador.service;

import despesas.gerenciador.model.Usuario;
import despesas.gerenciador.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Validated
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public boolean autenticarUsuario(String email, String senha) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(email);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            return usuario.getSenha().equals(senha);
        }
        return false;
    }

    @Transactional
    public Usuario criarUsuario(Usuario usuario) {
        usuario = usuarioRepository.save(usuario);
        return usuario;
    }

    public Usuario buscarUsuarioPorId(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
    }

    @Transactional
    public Usuario atualizarUsuario(Usuario usuario) {
        Usuario novoUsuario = buscarUsuarioPorId(usuario.getId());
        novoUsuario.setSenha(usuario.getSenha());
        novoUsuario.setEmail(usuario.getEmail());
        return usuarioRepository.save(novoUsuario);
    }

    public Optional<Usuario> deletarUsuario(Long id) {
        buscarUsuarioPorId(id);
        try {
            usuarioRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir, há entidades relacionadas!");
        }
        return usuarioRepository.findById(id);
    }

    public Long buscarIdDoUsuario(Usuario usuario) {
        Long id = usuario.getId();
        return id;
    }

    public Long buscarIdDoUsuarioPorEmail(String email) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);
        Usuario usuario =
                usuarioOptional.orElseThrow(() -> new NoSuchElementException("Usuário não encontrado para o email: " + email));
        return usuario.getId();
    }
}
