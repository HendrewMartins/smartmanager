package br.smartmanager.usuario;

import br.smartmanager.usuario.enums.Role;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuari", schema="PUBLIC")
@Entity
public class Usuario {

    @Id
    @Column(name = "usucodigo")
    private Integer id;

    @NotNull
    @Column(name = "usucodart")
    private Long codigoArtia;

    @NotNull
    @Column(name = "usunome")
    private String nome;

    @NotNull
    @Column(name = "usunomace")
    private String nomeAcesso;

    @Column(name = "usuadmin")
    private Boolean administrador;

    @Column(name= "usubaixa")
    private Boolean usuarioBaixa;

    @Column(name = "usuemail")
    private String email;

    @NotNull
    @Column(name = "ususenha")
    private String senha;

    @Column(name="roles")
    @Enumerated (EnumType.STRING)
    private Role roles;

}
