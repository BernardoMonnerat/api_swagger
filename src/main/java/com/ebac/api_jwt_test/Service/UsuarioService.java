package com.ebac.api_jwt_test.Service;

import com.ebac.api_jwt_test.Dto.UsuarioRequest;
import com.ebac.api_jwt_test.Dto.UsuarioResponse;
import com.ebac.api_jwt_test.Model.Usuario;
import com.ebac.api_jwt_test.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public void adicionarUsuario(UsuarioRequest request){
        Usuario usuario =  new Usuario();
        usuario.setNome(request.getNome());
        usuario.setSenha(request.getSenha());
        usuario.setAcessos(request.getAcessos());

        repository.save(usuario);
    }

    public UsuarioResponse buscarPorId(UUID uuid) {
        Usuario user = repository.findById(uuid)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + uuid));

        return new UsuarioResponse(
                user.getNome(),
                user.getSenha()
        );
    }
}
