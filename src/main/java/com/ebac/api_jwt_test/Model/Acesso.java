package com.ebac.api_jwt_test.Model;

import com.ebac.api_jwt_test.Enums.Tipo_Acesso;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "acesso")
@Table(name = "acessos")
public class Acesso implements Serializable, GrantedAuthority {

    private static final long serializableVersionID= 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipoAcesso")
    private Tipo_Acesso tipoAcesso;

    @ManyToMany(mappedBy = "acessos", fetch = FetchType.LAZY)
    private List<Usuario> usuarios = new ArrayList<>();

    public Acesso() {
    }

    public Acesso(UUID uuid, List<Usuario> usuarios, Tipo_Acesso tipoAcesso) {
        this.uuid = uuid;
        this.usuarios = usuarios;
        this.tipoAcesso = tipoAcesso;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Tipo_Acesso getTipoAcesso() {
        return tipoAcesso;
    }

    public void setTipoAcesso(Tipo_Acesso tipoAcesso) {
        this.tipoAcesso = tipoAcesso;
    }

    @Override
    public String getAuthority() {
        return tipoAcesso.toString();
    }
}
