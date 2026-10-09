package com.ebac.api_jwt_test.Controller;

import com.ebac.api_jwt_test.Dto.UsuarioRequest;
import com.ebac.api_jwt_test.Dto.UsuarioResponse;
import com.ebac.api_jwt_test.Service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> adicionarUsuario(@RequestBody UsuarioRequest request) {
        service.adicionarUsuario(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<UsuarioResponse> buscarPorId(@PathVariable UUID uuid) {
        UsuarioResponse response = service.buscarPorId(uuid);
        return ResponseEntity.ok(response);
    }
}
