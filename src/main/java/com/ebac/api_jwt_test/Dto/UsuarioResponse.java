package com.ebac.api_jwt_test.Dto;

public class UsuarioResponse {

    private String name;
    private String senha;

    public UsuarioResponse(String name, String senha) {
        this.name = name;
        this.senha = senha;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
