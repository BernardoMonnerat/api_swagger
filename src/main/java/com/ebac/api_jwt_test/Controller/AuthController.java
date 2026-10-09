package com.ebac.api_jwt_test.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtEncoder encoder;
    private final JwtEncoder jwtEncoder;

    public AuthController(JwtEncoder encoder, JwtEncoder jwtEncoder) {
        this.encoder = encoder;
        this.jwtEncoder = jwtEncoder;
    }


    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestParam String username){

        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .issuer("ebac-api-jwt-test")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(3600))
                .subject(username)
                .build();

        String token = jwtEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();

        return ResponseEntity.ok(Map.of("token", token));
    }
}
