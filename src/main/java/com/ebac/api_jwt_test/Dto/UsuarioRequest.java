package com.ebac.api_jwt_test.Dto;

import com.ebac.api_jwt_test.Model.Acesso;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

public class UsuarioRequest {

    @NotNull(message = "O nome nao pode ser nulo!")
    @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 carcteres!")
    private String nome;

    @NotNull(message = "A senha nao pde ser nula!")
    private String senha;

    @NotNull(message = "O usuario precisa de um tipo de acesso!")
    private List<Acesso> acessos = new ArrayList<>();

    public UsuarioRequest(String nome, String senha, List<Acesso> acessos) {
        this.nome = nome;
        this.senha = senha;
        this.acessos = acessos;
    }

    public List<Acesso> getAcessos() {
        return acessos;
    }

    public void setAcessos(List<Acesso> acessos) {
        this.acessos = acessos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
