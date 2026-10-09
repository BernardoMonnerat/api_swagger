package com.ebac.api_jwt_test.Model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity(name = "usuario")
@Table(name = "usuarios")
public class Usuario implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "senha")
    private String senha;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "usuario_Acesso",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "acesso_id")
    )
    private List<Acesso> acessos = new ArrayList<>();

    public Usuario() {
    }

    public Usuario(UUID uuid, String nome, String senha, List<Acesso> acessos) {
        this.uuid = uuid;
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

    public UUID geId() {
        return uuid;
    }

    public void setId(UUID uuid) {
        this.uuid = uuid;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return nome;
    }

}
