package com.ebac.api_jwt_test.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/itens")
@Tag(name ="Itens", description = "Operaçoes relacionadas a itens")
public class ItensController {

    @GetMapping("/listar-itens")
@Operation(summary = "busacr item", description = "Retornar o item")
    public ResponseEntity<String> buscarItem(@RequestParam String name) {
        return ResponseEntity.status(HttpStatus.OK).body("item: " + name);
    }
}
