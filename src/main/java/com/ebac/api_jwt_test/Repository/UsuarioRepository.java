package com.ebac.api_jwt_test.Repository;

import com.ebac.api_jwt_test.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
}
