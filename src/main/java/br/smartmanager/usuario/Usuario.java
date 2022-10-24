package br.smartmanager.usuario;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario")
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "usuarioId")
    private Integer id;


    @Column(name = "usucodart")
    private Long codigoArtia;

    @Column(name = "usunome")
    private String nome;

    @Column(name = "usunomace")
    private String nomeAcesso;

    @Column(name = "usuadmin")
    private Boolean administrador;

    @Column(name= "usubaixa")
    private Boolean usubaixa;

    @Column(name = "usuemail")
    private String email;

    @Column(name = "ususenha")
    private String senha;

}
